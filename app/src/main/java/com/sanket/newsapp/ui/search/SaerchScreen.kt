package com.sanket.newsapp.ui.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.sanket.newsapp.R
import com.sanket.newsapp.data.model.ApiArticle
import com.sanket.newsapp.ui.base.ShowError
import com.sanket.newsapp.ui.base.ShowLoading
import com.sanket.newsapp.ui.base.TopAppBar
import com.sanket.newsapp.ui.base.UiState
import com.sanket.newsapp.ui.topheadline.ArticleList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchNewsScreenRoute(
    onNewsClick: (sourceId: String) -> Unit,
    searchViewModel: SearchViewModel = hiltViewModel(),
    navController: NavController
) {

    val uiState: UiState<List<ApiArticle>> by searchViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(topBar = {
        TopAppBar(
            title = stringResource(id = R.string.screen_search_news), showBackArrow = true
        ) { navController.popBackStack() }
    }, content = { padding ->
        SearchNewsScreen(padding, uiState, searchViewModel, onNewsClick)
    })

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchNewsScreen(
    padding: PaddingValues,
    uiState: UiState<List<ApiArticle>>,
    searchViewModel: SearchViewModel,
    onNewsClick: (sourceId: String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray)
            .padding(padding),
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
    uiState: UiState<List<ApiArticle>>,
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
