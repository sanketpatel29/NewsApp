package com.sanket.newsapp.ui.language

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanket.newsapp.data.model.Language
import com.sanket.newsapp.data.model.Source
import com.sanket.newsapp.data.repository.LanguagesRepository
import com.sanket.newsapp.data.repository.NewsSourceRepository
import com.sanket.newsapp.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class LanguageViewModel(private val languagesRepository: LanguagesRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Language>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Language>>> = _uiState

    init {
        fetchNewsSources()
    }

    private fun fetchNewsSources() {
        viewModelScope.launch {
            languagesRepository.getLanguages()
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

}