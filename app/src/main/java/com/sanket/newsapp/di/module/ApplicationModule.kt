package com.sanket.newsapp.di.module

import android.content.Context
import androidx.room.Room
import com.sanket.newsapp.apputils.Constants
import com.sanket.newsapp.apputils.NetworkHelper
import com.sanket.newsapp.apputils.NetworkHelperImpl
import com.sanket.newsapp.apputils.logger.AppLogger
import com.sanket.newsapp.apputils.logger.Logger
import com.sanket.newsapp.data.api.HeaderInterceptor
import com.sanket.newsapp.data.api.NetworkService
import com.sanket.newsapp.data.local.AppDatabase
import com.sanket.newsapp.data.local.AppDatabaseService
import com.sanket.newsapp.data.local.DatabaseService
import com.sanket.newsapp.di.BaseUrl
import com.sanket.newsapp.di.DatabaseName
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = Constants.BASE_URL

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): NetworkService {
        return Retrofit.Builder().baseUrl(baseUrl).client(okHttpClient)
            .addConverterFactory(gsonConverterFactory).build().create(NetworkService::class.java)
    }

    @Singleton
    @Provides
    fun provideHttpClient(headerInterceptor: HeaderInterceptor): OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor(headerInterceptor).build()
    }

    @Singleton
    @Provides
    fun provideHeaderInterceptor(): HeaderInterceptor {
        return HeaderInterceptor()
    }

    @Provides
    @Singleton
    fun provideLogger(): Logger = AppLogger()

    @DatabaseName
    @Provides
    fun provideDatabaseName(): String = Constants.DATABASE_NAME

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
        @DatabaseName databaseName: String
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            databaseName
        ).build()
    }

    @Provides
    @Singleton
    fun provideDatabaseService(appDatabase: AppDatabase): DatabaseService {
        return AppDatabaseService(appDatabase)
    }

    @Singleton
    @Provides
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper {
        return NetworkHelperImpl(context = context)
    }
}