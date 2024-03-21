package com.sanket.newsapp.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanket.newsapp.data.model.ApiArticle
import com.sanket.newsapp.data.repository.TopHeadlineRepository
import com.sanket.newsapp.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val topHeadlineRepository: TopHeadlineRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<ApiArticle>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<ApiArticle>>> = _uiState

    var searchPhrase = MutableStateFlow("")

    init {
        fetchNewsBySearch(searchPhrase.value)
    }

    fun fetchNewsBySearch(phrase: String) {
        searchPhrase.value = phrase
        viewModelScope.launch {
            topHeadlineRepository.getNewsBySearch(searchPhrase.value).catch { e ->
                _uiState.value = UiState.Error(e.toString())
            }.collect {
                _uiState.value = UiState.Success(it)
            }
        }
    }
}