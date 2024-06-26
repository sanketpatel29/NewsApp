package com.sanket.newsapp.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sanket.newsapp.apputils.Constants
import com.sanket.newsapp.apputils.IOUtils
import com.sanket.newsapp.data.model.Country
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountriesRepository @Inject constructor(@ApplicationContext private val context: Context) {

    fun getCountries(): Flow<List<Country>> {

        return flow<List<Country>> {
            val countriesData = IOUtils.readJsonFromAssets(context, Constants.FILE_COUNTRIES)
            val gson = Gson()
            emit(gson.fromJson(countriesData, object : TypeToken<List<Country>>() {}.type))

        }.map { it -> it.filter { it.code.lowercase() in Constants.COUNTRIES_SUPPORTED_BY_NEWS_API } }
    }


}

