package com.sanket.newsapp.ui.newssource

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanket.newsapp.AppUtils.Constants.COUNTRY
import com.sanket.newsapp.data.model.Article
import com.sanket.newsapp.data.model.Source
import com.sanket.newsapp.data.repository.NewsSourceRepository
import com.sanket.newsapp.data.repository.TopHeadlineRepository
import com.sanket.newsapp.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsSourceViewModel(private val newsSourceRepository: NewsSourceRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Source>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Source>>> = _uiState

    init {
        fetchNewsSources()
    }

    private fun fetchNewsSources() {
        viewModelScope.launch {
            newsSourceRepository.getNewsSources()
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

}