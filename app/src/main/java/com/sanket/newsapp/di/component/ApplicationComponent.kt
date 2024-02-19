package com.sanket.newsapp.di.component

import android.content.Context
import com.sanket.newsapp.NewsApplication
import com.sanket.newsapp.data.api.NetworkService
import com.sanket.newsapp.data.repository.CountriesRepository
import com.sanket.newsapp.data.repository.NewsSourceRepository
import com.sanket.newsapp.data.repository.TopHeadlineRepository
import com.sanket.newsapp.di.ApplicationContext
import com.sanket.newsapp.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    fun inject(application: NewsApplication)

    @ApplicationContext
    fun getContext(): Context

    fun getNetworkService(): NetworkService

    fun getTopHeadlineRepository(): TopHeadlineRepository

    fun getNewsSourceRepository(): NewsSourceRepository

    fun getCountriesRepository(): CountriesRepository

}