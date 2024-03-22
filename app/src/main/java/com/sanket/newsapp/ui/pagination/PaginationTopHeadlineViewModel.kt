package com.sanket.newsapp.ui.pagination

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.sanket.newsapp.data.model.ApiArticle
import com.sanket.newsapp.data.repository.TopHeadlineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PaginationTopHeadlineViewModel @Inject constructor(
    private val topHeadlineRepository: TopHeadlineRepository
) : ViewModel() {

    private val _topHeadlineUiState =
        MutableStateFlow<PagingData<ApiArticle>>(value = PagingData.empty())

    val topHeadlineUiState: StateFlow<PagingData<ApiArticle>> = _topHeadlineUiState

    init {
        startFetchingArticles()
    }

    private fun startFetchingArticles() {
        viewModelScope.launch(Dispatchers.Main) {
            topHeadlineRepository.getTopHeadlinesArticles()
                .flowOn(Dispatchers.IO)
                .catch { }
                .collect {
                    _topHeadlineUiState.value = it
                }
        }
    }

}