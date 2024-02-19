package com.sanket.newsapp.ui.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sanket.newsapp.data.model.Country
import com.sanket.newsapp.data.repository.CountriesRepository
import com.sanket.newsapp.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CountriesViewModel(private val countriesRepository: CountriesRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Country>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Country>>> = _uiState

    init {
        fetchCountries()
    }

    private fun fetchCountries() {
        viewModelScope.launch {
            countriesRepository.getCountries()
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }.collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }
}