package com.sanket.newsapp.ui.topheadline

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.sanket.newsapp.data.model.ApiArticle
import com.sanket.newsapp.ui.base.ShowError
import com.sanket.newsapp.ui.base.ShowLoading
import com.sanket.newsapp.ui.base.TopAppBar
import com.sanket.newsapp.ui.base.UiState


@Preview
@Composable
fun TopHeadLineRoute(
    onNewsClick: (url: String) -> Unit = {},
    viewModel: TopHeadlineViewModel = hiltViewModel(),
    newsType: String = "",
    newsIdentifier: String = "",
    navController: NavController
) {

    val topHeadlineUiState: UiState<List<ApiArticle>> by viewModel.uiState.collectAsStateWithLifecycle()
    var title = "Top HeadLines"

    Scaffold(topBar = {
        TopAppBar(
            title = title, showBackArrow = true
        ) { navController.popBackStack() }
    }, content = { padding ->

        LaunchedEffect(key1 = Unit, block = {
            viewModel.fetchNews(newsType, newsIdentifier)
        })

        Column(modifier = Modifier.padding(padding)) {
            TopHeadlineScreen(topHeadlineUiState, onNewsClick)
        }
    })

}

@Composable
fun ArticleList(apiArticles: List<ApiArticle>, onNewsClick: (url: String) -> Unit) {
    LazyColumn {
        items(apiArticles, key = { article -> article.url }) { article ->
            Article(article, onNewsClick)
        }
    }
}

@Composable
fun Article(apiArticle: ApiArticle, onNewsClick: (url: String) -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .clickable {
            if (apiArticle.url.isNotEmpty()) {
                onNewsClick(apiArticle.url)
            }
        }) {
        BannerImage(apiArticle.imageUrl, apiArticle.title)
        TitleText(apiArticle.title)
        DescriptionText(apiArticle.description)
        SourceText(apiArticle.apiSource.name)
    }
}

@Composable
fun SourceText(name: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.titleSmall,
        color = Color.Gray,
        maxLines = 1,
        modifier = Modifier.padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 8.dp)
    )
}

@Preview
@Composable
fun DescriptionText(description: String = "description") {

    if (!description.isNullOrEmpty())
        Text(
            text = description,
            style = MaterialTheme.typography.titleSmall,
            color = Color.Gray,
            maxLines = 1,
            modifier = Modifier.padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 8.dp)
        )
}

@Preview
@Composable
fun TitleText(title: String = "Title") {

    if (!title.isNullOrEmpty())
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black,
            maxLines = 2,
            modifier = Modifier.padding(4.dp)
        )
}

@Composable
fun BannerImage(imageUrl: String?, title: String) {
    AsyncImage(
        model = imageUrl,
        contentDescription = title,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
    )
}

@Composable
fun TopHeadlineScreen(
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
