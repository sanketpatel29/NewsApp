package com.sanket.newsapp.ui.newssource

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
import com.sanket.newsapp.data.model.Source
import com.sanket.newsapp.ui.base.ShowError
import com.sanket.newsapp.ui.base.ShowLoading
import com.sanket.newsapp.ui.base.UiState

@Composable
fun SourcesScreenRoute(
    onNewsClick: (sourceId: String) -> Unit,
    newsSourceViewModel: NewsSourceViewModel = hiltViewModel()
) {

    val newsSourceUiState: UiState<List<Source>> by newsSourceViewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SourcesScreen(newsSourceUiState, onNewsClick)
    }
}

@Composable
fun SourcesScreen(
    newsSourceUiState: UiState<List<Source>>,
    onNewsClick: (sourceId: String) -> Unit
) {
    when (newsSourceUiState) {
        is UiState.Error -> {
            ShowError(text = newsSourceUiState.message)
        }

        is UiState.Loading -> {
            ShowLoading()
        }

        is UiState.Success -> {
            ShowSourcesList(newsSourceUiState.data, onNewsClick)
        }
    }

}

@Composable
fun ShowSourcesList(data: List<Source>, onNewsClick: (sourceId: String) -> Unit) {
    LazyColumn {
        items(data.size) { index -> ShowSource(data[index], onNewsClick) }
    }
}

@Composable
fun ShowSource(source: Source, onNewsClick: (sourceId: String) -> Unit) {
    Button(
        onClick = { source.id.let(onNewsClick) },
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .padding(start = 20.dp, top = 5.dp, end = 20.dp, bottom = 5.dp)
            .fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {
        Text(
            text = source.name,
            color = Color.White,
            fontSize = 16.sp
        )
    }
}
