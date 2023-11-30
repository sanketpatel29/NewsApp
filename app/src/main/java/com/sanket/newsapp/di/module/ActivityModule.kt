package com.sanket.newsapp.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sanket.newsapp.data.repository.TopHeadlineRepository
import com.sanket.newsapp.di.ActivityContext
import com.sanket.newsapp.ui.base.ViewModelProviderFactory
import com.sanket.newsapp.ui.topheadline.TopHeadlineAdapter
import com.sanket.newsapp.ui.topheadline.TopHeadlineViewModel
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @ActivityContext
    @Provides
    fun provideContext(): Context {
        return activity
    }

    @Provides
    fun provideNewsListViewModel(topHeadlineRepository: TopHeadlineRepository): TopHeadlineViewModel {
        return ViewModelProvider(activity,
            ViewModelProviderFactory(TopHeadlineViewModel::class) {
                TopHeadlineViewModel(topHeadlineRepository)
            })[TopHeadlineViewModel::class.java]
    }

    @Provides
    fun provideTopHeadlineAdapter() = TopHeadlineAdapter(ArrayList())

}