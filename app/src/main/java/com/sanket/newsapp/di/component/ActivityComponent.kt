package com.sanket.newsapp.di.component

import com.sanket.newsapp.di.ActivityScope
import com.sanket.newsapp.di.module.ActivityModule
import com.sanket.newsapp.ui.countries.CountriesActivity
import com.sanket.newsapp.ui.language.LanguagesActivity
import com.sanket.newsapp.ui.newssource.NewsSourcesActivity
import com.sanket.newsapp.ui.topheadline.TopHeadlineActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: TopHeadlineActivity)
    fun inject(activity: NewsSourcesActivity)
    fun inject(activity: CountriesActivity)
    fun inject(activity: LanguagesActivity)

}