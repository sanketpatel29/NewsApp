package com.sanket.newsapp.ui.topheadline

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanket.newsapp.apputils.Constants.COUNTRY
import com.sanket.newsapp.apputils.Logger
import com.sanket.newsapp.data.model.Article
import com.sanket.newsapp.data.repository.TopHeadlineRepository
import com.sanket.newsapp.ui.base.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.launch

class TopHeadlineViewModel(private val topHeadlineRepository: TopHeadlineRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    fun fetchNews() {
        viewModelScope.launch {
            topHeadlineRepository.getTopHeadlines(COUNTRY).catch { e ->
                _uiState.value = UiState.Error(e.toString())
            }.collect {
                _uiState.value = UiState.Success(it)
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
        Logger().d(TopHeadlineViewModel::class.java, "lang1= $lang1 and lang2=$lang2")
        viewModelScope.launch(Dispatchers.IO) {

            topHeadlineRepository.getNewsByLanguages(lang1)
                .zip(topHeadlineRepository.getNewsByLanguages(lang2)) { result1, result2 ->
                    val articles = mutableListOf<Article>()
                    articles.addAll(result1)
                    articles.addAll(result2)
                    return@zip articles
                }.flowOn(Dispatchers.IO)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}