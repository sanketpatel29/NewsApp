package com.sanket.newsapp.ui.language

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sanket.newsapp.data.model.Language
import com.sanket.newsapp.ui.base.ShowError
import com.sanket.newsapp.ui.base.ShowLoading
import com.sanket.newsapp.ui.base.UiState

@Composable
fun LanguagesScreenRoute(
    onNewsClick: (countryCode: String) -> Unit,
    countriesViewModel: LanguageViewModel = hiltViewModel()
) {

    val languageUiState: UiState<List<Language>> by countriesViewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LanguagesScreen(languageUiState, onNewsClick)
    }
}

@Composable
fun LanguagesScreen(
    languageUiState: UiState<List<Language>>,
    onNewsClick: (sourceId: String) -> Unit
) {
    when (languageUiState) {
        is UiState.Error -> {
            ShowError(text = languageUiState.message)
        }

        is UiState.Loading -> {
            ShowLoading()
        }

        is UiState.Success -> {
            ShowLanguagesList(languageUiState.data, onNewsClick)
        }
    }

}

@Composable
fun ShowLanguagesList(data: List<Language>, onNewsClick: (sourceId: String) -> Unit) {
    LazyColumn {
        items(data.size) { index -> ShowLanguage(data[index], onNewsClick) }
    }
}

@Composable
fun ShowLanguage(language: Language, onNewsClick: (sourceId: String) -> Unit) {
    Button(
        onClick = { language.code.let(onNewsClick) },
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .padding(start = 20.dp, top = 5.dp, end = 20.dp, bottom = 5.dp)
            .fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {
        Text(
            text = language.name,
            color = Color.White,
            fontSize = 16.sp
        )
    }
}
