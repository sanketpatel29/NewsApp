package com.sanket.newsapp.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sanket.newsapp.apputils.Constants
import com.sanket.newsapp.apputils.IOUtils
import com.sanket.newsapp.data.model.Language
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LanguagesRepository @Inject constructor(@ApplicationContext private val context: Context) {

    fun getLanguages(): Flow<List<Language>> {

        return flow<List<Language>> {
            val countriesData = IOUtils.readJsonFromAssets(context, Constants.FILE_LANGUAGES)
            emit(Gson().fromJson(countriesData, object : TypeToken<List<Language>>() {}.type))

        }.map { it }
    }
}

