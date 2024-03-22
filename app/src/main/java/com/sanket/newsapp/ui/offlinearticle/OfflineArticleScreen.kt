package com.sanket.newsapp.ui.offlinearticle

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.sanket.newsapp.R
import com.sanket.newsapp.data.local.entity.Article
import com.sanket.newsapp.ui.base.ShowError
import com.sanket.newsapp.ui.base.ShowLoading
import com.sanket.newsapp.ui.base.TopAppBar
import com.sanket.newsapp.ui.base.UiState
import com.sanket.newsapp.ui.topheadline.BannerImage
import com.sanket.newsapp.ui.topheadline.DescriptionText
import com.sanket.newsapp.ui.topheadline.SourceText
import com.sanket.newsapp.ui.topheadline.TitleText


@Preview
@Composable
fun OfflineArticleRoute(
    onNewsClick: (url: String) -> Unit = {},
    viewModel: OfflineArticleViewModel = hiltViewModel(),
    navController: NavController

) {
    val topHeadlineUiState: UiState<List<Article>> by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(topBar = {
        TopAppBar(
            title = stringResource(id = R.string.screen_offline_article), showBackArrow = true
        ) { navController.popBackStack() }
    }, content = { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TopHeadlineScreen(topHeadlineUiState, onNewsClick)
        }
    })
}

@Composable
fun TopHeadlineScreen(
    uiState: UiState<List<Article>>,
    onNewsClick: (url: String) -> Unit
) {
    when (uiState) {
        is UiState.Success -> {
            ArticleList(articles = uiState.data, onNewsClick = onNewsClick)
        }

        is UiState.Loading -> {
            ShowLoading()
        }

        is UiState.Error -> {
            ShowError(text = uiState.message)
        }

        else -> {}
    }
}

@Composable
fun ArticleList(articles: List<Article>, onNewsClick: (url: String) -> Unit) {
    LazyColumn {
        items(articles, key = { article -> article.url }) { article ->
            Article(article, onNewsClick)
        }
    }
}

@Composable
fun Article(article: Article, onNewsClick: (url: String) -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            if (article.url.isNotEmpty()) {
                onNewsClick(article.url)
            }
        }) {
        BannerImage(article.imageUrl, article.title)
        TitleText(article.title)
        article.description?.let { DescriptionText(it) }
        article.source.name?.let { SourceText(it) }
    }
}