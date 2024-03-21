package com.sanket.newsapp.ui.newssource

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanket.newsapp.data.model.ApiSource
import com.sanket.newsapp.data.repository.NewsSourceRepository
import com.sanket.newsapp.ui.base.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsSourceViewModel @Inject constructor(private val newsSourceRepository: NewsSourceRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<ApiSource>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<ApiSource>>> = _uiState

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