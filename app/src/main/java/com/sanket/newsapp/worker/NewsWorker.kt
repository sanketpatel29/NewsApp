package com.sanket.newsapp.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.sanket.newsapp.apputils.Constants
import com.sanket.newsapp.apputils.logger.AppLogger
import com.sanket.newsapp.data.api.NetworkService
import com.sanket.newsapp.data.local.DatabaseService
import com.sanket.newsapp.data.model.toArticle
import com.sanket.newsapp.notifications.NotificationHelper
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class NewsWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val parameters: WorkerParameters,
    private val networkService: NetworkService,
    private val databaseService: DatabaseService,
    private val notificationHelper: NotificationHelper
) : CoroutineWorker(context, parameters) {
    override suspend fun doWork(): Result {

        lateinit var result: Result

        kotlin.runCatching {
            AppLogger().d(NewsWorker::class.java, "Worker for daily news called")
            val topHeadLineResponse = networkService.getTopHeadlines(Constants.DEFAULT_COUNTRY)
            val articles =
                topHeadLineResponse.apiArticles.map { apiArticle -> apiArticle.toArticle() }
            databaseService.deleteAndInsertNewsArticles(articles)
        }.onSuccess {

            notificationHelper.createNotificationChannel()
            notificationHelper.showNotification(
                Constants.Notification.Content.TITLE,
                Constants.Notification.Content.DESCRIPTION
            )
            result = Result.success()
        }.onFailure {
            result = Result.retry()
        }
        return result
    }
}