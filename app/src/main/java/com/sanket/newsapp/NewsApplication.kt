package com.sanket.newsapp

import android.app.Application
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import androidx.work.Constraints
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import com.sanket.newsapp.apputils.Constants
import com.sanket.newsapp.apputils.TimeUtil
import com.sanket.newsapp.worker.NewsWorker
import dagger.hilt.android.HiltAndroidApp
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.random.Random

@HiltAndroidApp
class NewsApplication : Application(), Configuration.Provider {
    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    @Inject
    lateinit var workManager: WorkManager

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

    override fun onCreate() {
        super.onCreate()
        initWorkManager()
    }

    private fun initWorkManager() {

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val workRequest = PeriodicWorkRequest.Builder(
            NewsWorker::class.java,
            24,
            TimeUnit.HOURS
        )
            .setConstraints(constraints)
            .setInitialDelay(
                TimeUtil.getInitialDelay(
                    Constants.Worker.DAILY_NEWS_UPDATE_HOURS,
                    Random.nextInt(0, 59)
                ),
                TimeUnit.MILLISECONDS
            )
            .addTag(Constants.Worker.DAILY_NEWS_UPDATE)
            .build()

        workManager.enqueueUniquePeriodicWork(
            Constants.Worker.DAILY_NEWS_UPDATE,
            ExistingPeriodicWorkPolicy.CANCEL_AND_REENQUEUE, workRequest
        )
    }

}