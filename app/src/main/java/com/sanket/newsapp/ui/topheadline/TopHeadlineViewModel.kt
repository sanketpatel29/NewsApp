package com.sanket.newsapp.ui.topheadline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanket.newsapp.apputils.Constants
import com.sanket.newsapp.apputils.Constants.DEFAULT_COUNTRY
import com.sanket.newsapp.apputils.logger.Logger
import com.sanket.newsapp.data.model.ApiArticle
import com.sanket.newsapp.data.repository.TopHeadlineRepository
import com.sanket.newsapp.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopHeadlineViewModel @Inject constructor(
    private val topHeadlineRepository: TopHeadlineRepository,
    private val logger: Logger
) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<ApiArticle>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<ApiArticle>>> = _uiState

    private fun fetchNews() {
        viewModelScope.launch {
            topHeadlineRepository.getTopHeadlines(DEFAULT_COUNTRY).catch { e ->
                _uiState.value = UiState.Error(e.toString())
            }.collect {
                _uiState.value = UiState.Success(it)
            }
        }
    }

    fun fetchNews(newsType: String, newsIdentifier: String) {
        when (newsType) {
            Constants.NewsBy.IntentParam.Value.COUNTRY -> {
                fetchNewsByCountry(newsIdentifier)
            }

            Constants.NewsBy.IntentParam.Value.SOURCE -> {
                fetchNewsBySource(newsIdentifier)
            }

            Constants.NewsBy.IntentParam.Value.LANGUAGE -> {
                fetchNewsByLanguage(newsIdentifier)
            }

            else -> {
                fetchNews()
            }
        }
    }

    fun fetchNewsByCountry(country: String) {
        viewModelScope.launch {
            topHeadlineRepository.getNewsByCountry(country).catch { e ->
                _uiState.value = UiState.Error(e.toString())
            }.collect {
                _uiState.value = UiState.Success(it)
            }
        }
    }

    fun fetchNewsBySource(source: String) {
        viewModelScope.launch {
            topHeadlineRepository.getNewsBySources(source).catch { e ->
                _uiState.value = UiState.Error(e.toString())
            }.collect {
                _uiState.value = UiState.Success(it)
            }
        }
    }

    fun fetchNewsByLanguage(language: String) {
        viewModelScope.launch {
            topHeadlineRepository.getNewsByLanguages(language).catch { e ->
                _uiState.value = UiState.Error(e.toString())
            }.collect {
                _uiState.value = UiState.Success(it)
            }
        }
    }

    fun fetchNewsByLanguages(languages: List<String>) {

        var lang1 = languages[0].trim()
        var lang2 = languages[1].trim()
        logger.d(TopHeadlineViewModel::class.java, "lang1= $lang1 and lang2=$lang2")
        viewModelScope.launch(Dispatchers.IO) {

            topHeadlineRepository.getNewsByLanguages(lang1)
                .zip(topHeadlineRepository.getNewsByLanguages(lang2)) { result1, result2 ->
                    val apiArticles = mutableListOf<ApiArticle>()
                    apiArticles.addAll(result1)
                    apiArticles.addAll(result2)
                    return@zip apiArticles
                }.flowOn(Dispatchers.IO)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    it.shuffle()
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}