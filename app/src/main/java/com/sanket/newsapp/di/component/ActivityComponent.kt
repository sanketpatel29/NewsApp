package com.sanket.newsapp.di.component

import com.sanket.newsapp.di.ActivityScope
import com.sanket.newsapp.di.module.ActivityModule
import com.sanket.newsapp.ui.topheadline.TopHeadlineActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: TopHeadlineActivity)

}