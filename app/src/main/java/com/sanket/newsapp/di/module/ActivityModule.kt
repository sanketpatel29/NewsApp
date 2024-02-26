package com.sanket.newsapp.di.module

import com.sanket.newsapp.ui.countries.CountriesAdapter
import com.sanket.newsapp.ui.language.LanguagesAdapter
import com.sanket.newsapp.ui.newssource.NewsSourcesAdapter
import com.sanket.newsapp.ui.topheadline.TopHeadlineAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule() {

    @ActivityScoped
    @Provides
    fun provideTopHeadlineAdapter() = TopHeadlineAdapter(ArrayList())

    @ActivityScoped
    @Provides
    fun provideNewsSourceAdapter() = NewsSourcesAdapter(ArrayList())

    @ActivityScoped
    @Provides
    fun provideCountriesAdapter() = CountriesAdapter(ArrayList())

    @ActivityScoped
    @Provides
    fun provideLanguageAdapter() = LanguagesAdapter(ArrayList())

}