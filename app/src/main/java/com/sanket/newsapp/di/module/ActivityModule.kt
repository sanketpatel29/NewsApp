package com.sanket.newsapp.di.module

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.sanket.newsapp.data.repository.CountriesRepository
import com.sanket.newsapp.data.repository.LanguagesRepository
import com.sanket.newsapp.data.repository.NewsSourceRepository
import com.sanket.newsapp.data.repository.TopHeadlineRepository
import com.sanket.newsapp.di.ActivityContext
import com.sanket.newsapp.ui.base.ViewModelProviderFactory
import com.sanket.newsapp.ui.countries.CountriesAdapter
import com.sanket.newsapp.ui.countries.CountriesViewModel
import com.sanket.newsapp.ui.language.LanguageViewModel
import com.sanket.newsapp.ui.language.LanguagesAdapter
import com.sanket.newsapp.ui.newssource.NewsSourceViewModel
import com.sanket.newsapp.ui.newssource.NewsSourcesAdapter
import com.sanket.newsapp.ui.search.SearchViewModel
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
        return ViewModelProvider(activity, ViewModelProviderFactory(TopHeadlineViewModel::class) {
            TopHeadlineViewModel(topHeadlineRepository)
        })[TopHeadlineViewModel::class.java]
    }

    @Provides
    fun provideTopHeadlineAdapter() = TopHeadlineAdapter(ArrayList())

    @Provides
    fun provideNewsSourceViewModel(newsSourceRepository: NewsSourceRepository): NewsSourceViewModel {
        return ViewModelProvider(activity, ViewModelProviderFactory(NewsSourceViewModel::class) {
            NewsSourceViewModel(newsSourceRepository)
        })[NewsSourceViewModel::class.java]
    }

    @Provides
    fun provideNewsSourceAdapter() = NewsSourcesAdapter(ArrayList())

    @Provides
    fun provideCountriesViewModel(countriesRepository: CountriesRepository): CountriesViewModel {
        return ViewModelProvider(activity, ViewModelProviderFactory(CountriesViewModel::class) {
            CountriesViewModel(countriesRepository)
        })[CountriesViewModel::class.java]
    }

    @Provides
    fun provideCountriesAdapter() = CountriesAdapter(ArrayList())

    @Provides
    fun provideLanguageAdapter() = LanguagesAdapter(ArrayList())

    @Provides
    fun provideLanguageViewModel(languagesRepository: LanguagesRepository): LanguageViewModel {

        return ViewModelProvider(activity, ViewModelProviderFactory(LanguageViewModel::class) {
            LanguageViewModel(languagesRepository)
        })[LanguageViewModel::class.java]
    }

    @Provides
    fun provideSearchViewModel(topHeadlineRepository: TopHeadlineRepository): SearchViewModel {

        return ViewModelProvider(activity, ViewModelProviderFactory(SearchViewModel::class) {
            SearchViewModel(topHeadlineRepository)
        })[SearchViewModel::class.java]
    }
}