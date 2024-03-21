package com.sanket.newsapp.ui.offlinearticle

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanket.newsapp.apputils.Constants.COUNTRY
import com.sanket.newsapp.apputils.NetworkHelper
import com.sanket.newsapp.apputils.logger.Logger
import com.sanket.newsapp.data.local.entity.Article
import com.sanket.newsapp.data.repository.TopHeadlineRepository
import com.sanket.newsapp.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OfflineArticleViewModel @Inject constructor(
    private val topHeadlineRepository: TopHeadlineRepository,
    private val logger: Logger,
    private val networkHelper: NetworkHelper,
) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Article>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Article>>> = _uiState

    init {
        if (networkHelper.isNetworkAvailable()) {
            fetchNewsArticles()
        } else {
            fetchNewsArticlesFromDb()
        }
    }

    private fun fetchNewsArticles() {
        viewModelScope.launch {
            topHeadlineRepository.getNewsArticles(COUNTRY)
                .flowOn(Dispatchers.IO)
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

    private fun fetchNewsArticlesFromDb() {
        viewModelScope.launch {
            topHeadlineRepository.getNewsArticlesDirectFromDb(COUNTRY).catch { e ->
                _uiState.value = UiState.Error(e.toString())
            }.collect {
                _uiState.value = UiState.Success(it)
            }
        }
    }

}