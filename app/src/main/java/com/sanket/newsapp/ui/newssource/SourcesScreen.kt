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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.sanket.newsapp.R
import com.sanket.newsapp.data.model.ApiSource
import com.sanket.newsapp.ui.base.ShowError
import com.sanket.newsapp.ui.base.ShowLoading
import com.sanket.newsapp.ui.base.TopAppBar
import com.sanket.newsapp.ui.base.UiState

@Composable
fun SourcesScreenRoute(
    onNewsClick: (sourceId: String) -> Unit,
    newsSourceViewModel: NewsSourceViewModel = hiltViewModel(),
    navController: NavController
) {

    val newsApiSourceUiState: UiState<List<ApiSource>> by newsSourceViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(topBar = {
        TopAppBar(
            title = stringResource(id = R.string.screen_news_sources), showBackArrow = true
        ) { navController.popBackStack() }
    }, content = { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            SourcesScreen(newsApiSourceUiState, onNewsClick)
        }
    })
}

@Composable
fun SourcesScreen(
    newsApiSourceUiState: UiState<List<ApiSource>>,
    onNewsClick: (sourceId: String) -> Unit
) {
    when (newsApiSourceUiState) {
        is UiState.Error -> {
            ShowError(text = newsApiSourceUiState.message)
        }

        is UiState.Loading -> {
            ShowLoading()
        }

        is UiState.Success -> {
            ShowSourcesList(newsApiSourceUiState.data, onNewsClick)
        }
    }

}

@Composable
fun ShowSourcesList(data: List<ApiSource>, onNewsClick: (sourceId: String) -> Unit) {
    LazyColumn {
        items(data.size) { index -> ShowSource(data[index], onNewsClick) }
    }
}

@Composable
fun ShowSource(apiSource: ApiSource, onNewsClick: (sourceId: String) -> Unit) {
    Button(
        onClick = { apiSource.id.let(onNewsClick) },
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .padding(start = 20.dp, top = 5.dp, end = 20.dp, bottom = 5.dp)
            .fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {
        Text(
            text = apiSource.name,
            color = Color.White,
            fontSize = 16.sp
        )
    }
}
