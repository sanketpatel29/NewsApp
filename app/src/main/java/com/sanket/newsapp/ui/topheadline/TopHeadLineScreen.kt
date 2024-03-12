package com.sanket.newsapp.ui.topheadline

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
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
import coil.compose.AsyncImage
import com.sanket.newsapp.apputils.Constants
import com.sanket.newsapp.data.model.Article
import com.sanket.newsapp.data.model.Source
import com.sanket.newsapp.ui.base.ShowError
import com.sanket.newsapp.ui.base.ShowLoading
import com.sanket.newsapp.ui.base.UiState


@Preview
@Composable
fun TopHeadLineRoute(
    onNewsClick: (url: String) -> Unit = {},
    viewModel: TopHeadlineViewModel = hiltViewModel(),
    newsType: String = "",
    newsIdentifier: String = ""
) {
    val topHeadlineUiState: UiState<List<Article>> by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = Unit, block = {
        when (newsType) {
            Constants.NewsBy.IntentParam.Value.COUNTRY -> {
                viewModel.fetchNewsByCountry(newsIdentifier)
            }

            Constants.NewsBy.IntentParam.Value.SOURCE -> {
                viewModel.fetchNewsBySource(newsIdentifier)
            }

            Constants.NewsBy.IntentParam.Value.LANGUAGE -> {
                viewModel.fetchNewsByLanguage(newsIdentifier)
            }

            else -> {
                viewModel.fetchNews()
            }
        }
    })

    Column(modifier = Modifier.padding(4.dp)) {
        TopHeadlineScreen(topHeadlineUiState, onNewsClick)
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
        BannerImage(article)
        TitleText(article.title)
        DescriptionText(article.description)
        SourceText(article.source)
    }
}

@Composable
fun SourceText(source: Source) {
    Text(
        text = source.name,
        style = MaterialTheme.typography.titleSmall,
        color = Color.Gray,
        maxLines = 1,
        modifier = Modifier.padding(start = 4.dp, end = 4.dp, top = 4.dp, bottom = 8.dp)
    )
}

@Preview
@Composable
fun DescriptionText(description: String = "description") {

    if (description.isNotEmpty())
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
fun BannerImage(article: Article) {
    AsyncImage(
        model = article.imageUrl,
        contentDescription = article.title,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
    )
}

@Composable
fun TopHeadlineScreen(
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
