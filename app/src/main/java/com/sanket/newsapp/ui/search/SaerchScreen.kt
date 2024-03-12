package com.sanket.newsapp.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sanket.newsapp.data.model.Article
import com.sanket.newsapp.ui.base.ShowError
import com.sanket.newsapp.ui.base.ShowLoading
import com.sanket.newsapp.ui.base.UiState
import com.sanket.newsapp.ui.topheadline.ArticleList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchNewsScreenRoute(
    onNewsClick: (sourceId: String) -> Unit,
    searchViewModel: SearchViewModel = hiltViewModel()
) {

    val uiState: UiState<List<Article>> by searchViewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        var text by remember { mutableStateOf("") }
        var active by remember { mutableStateOf(false) }


        SearchBar(
            query = text,
            onQueryChange = {
                text = it
                searchViewModel.fetchNewsBySearch(text)
            },
            onSearch = {
                active = false
            },
            placeholder = {
                Text(text = "Search news")
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    tint = MaterialTheme.colorScheme.onSurface,
                    contentDescription = null
                )
            },
            trailingIcon = {},
            content = {
                SearchNewsList(uiState, onNewsClick)

            },
            active = active,
            onActiveChange = {
                active = it
            },
            tonalElevation = 0.dp
        )
    }
}

@Composable
fun SearchNewsList(
    uiState: UiState<List<Article>>,
    onNewsClick: (url: String) -> Unit
) {
    when (uiState) {
        is UiState.Success -> {
            ArticleList(uiState.data, onNewsClick)
        }

        is UiState.Loading -> {
            ShowLoading()
        }

        is UiState.Error -> {
            ShowError(text = uiState.message)
        }
    }
}
