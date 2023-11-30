package com.sanket.newsapp.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.sanket.newsapp.NewsApplication
import com.sanket.newsapp.di.ActivityContext
import com.sanket.newsapp.di.ApplicationContext
import com.sanket.newsapp.ui.topheadline.TopHeadlineActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: AppCompatActivity) {

    @ActivityContext
    @Provides
    fun provideContext(): Context {
        return activity
    }
}