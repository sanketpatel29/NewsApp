package com.sanket.newsapp.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApplicationContext

@Qualifier
@Retention(AnnotationRetention.SOURCE)
annotation class ActivityContext
