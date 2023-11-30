package com.sanket.newsapp.di.module

import android.content.Context
import com.sanket.newsapp.NewsApplication
import com.sanket.newsapp.di.ApplicationContext
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val application: NewsApplication) {

    @ApplicationContext
    @Provides
    fun provideContext(): Context {
        return application
    }

}