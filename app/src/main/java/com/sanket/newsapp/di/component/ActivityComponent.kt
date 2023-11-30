package me.amitshekhar.newsapp.di.component

import dagger.Component
import me.amitshekhar.newsapp.di.ActivityScope
import me.amitshekhar.newsapp.di.module.ActivityModule
import me.amitshekhar.newsapp.ui.topheadline.TopHeadlineActivity

@ActivityScope
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(activity: TopHeadlineActivity)

}