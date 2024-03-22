package com.sanket.newsapp.ui.pagination

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.sanket.newsapp.R
import com.sanket.newsapp.data.model.ApiArticle
import com.sanket.newsapp.ui.base.ShowError
import com.sanket.newsapp.ui.base.ShowLoading
import com.sanket.newsapp.ui.base.TopAppBar
import com.sanket.newsapp.ui.topheadline.BannerImage
import com.sanket.newsapp.ui.topheadline.DescriptionText
import com.sanket.newsapp.ui.topheadline.SourceText
import com.sanket.newsapp.ui.topheadline.TitleText

@Composable
fun PaginationTopHeadlineRoute(
    onNewsClick: (url: String) -> Unit,
    navController: NavController,
    topHeadlineViewModel: PaginationTopHeadlineViewModel = hiltViewModel()
) {
    val topHeadlineUiState = topHeadlineViewModel.topHeadlineUiState.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                title = stringResource(id = R.string.screen_top_headline_pagination),
                showBackArrow = true
            )
            { navController.popBackStack() }
        },
        content = { padding ->
            Column(modifier = Modifier.padding(padding)) {
                TopHeadlineScreen(topHeadlineUiState, onNewsClick)
            }
        })
}

@Composable
fun TopHeadlineScreen(
    articles: LazyPagingItems<ApiArticle>,
    onNewsClick: (url: String) -> Unit
) {

    ArticleList(articles, onNewsClick)

    articles.apply {
        when {
            loadState.refresh is LoadState.Loading -> {
                ShowLoading()
            }

            loadState.refresh is LoadState.Error -> {
                val error = articles.loadState.refresh as LoadState.Error
                ShowError(error.error.localizedMessage!!)
            }

            loadState.append is LoadState.Loading -> {
                ShowLoading()
            }

            loadState.append is LoadState.Error -> {
                val error = articles.loadState.append as LoadState.Error
                ShowError(error.error.localizedMessage!!)
            }
        }
    }

}

@Composable
fun ArticleList(articles: LazyPagingItems<ApiArticle>, onNewsClick: (url: String) -> Unit) {
    LazyColumn {
        items(articles.itemCount, key = { index -> articles[index]!!.url }) { index ->
            ApiArticle(articles[index]!!, onNewsClick)
        }
    }
}

@Composable
fun ApiArticle(article: ApiArticle, onNewsClick: (url: String) -> Unit) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .shadow(4.dp)
            .padding(6.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5))
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .clickable {
                if (article.url.isNotEmpty()) {
                    onNewsClick(article.url)
                }
            }) {

            BannerImage(imageUrl = article.imageUrl, title = article.title)
            TitleText(article.title)
            DescriptionText(article.description)
            SourceText(article.apiSource.name)
        }
    }

}
