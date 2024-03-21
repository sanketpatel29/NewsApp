package com.sanket.newsapp.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sanket.newsapp.R
import com.sanket.newsapp.ui.base.Route
import com.sanket.newsapp.ui.base.TopAppBar

@Preview
@Composable
fun HomeScreenRoute(navController: NavController = rememberNavController()) {

    Scaffold(topBar = {
        TopAppBar(stringResource(id = R.string.app_name), showBackArrow = false) {
        }
    }, content = { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HomeScreen(navController)
        }
    })

}

@Preview
@Composable
fun HomeScreen(navController: NavController = rememberNavController()) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SimpleButton(
            stringResource(id = R.string.screen_top_headline),
            Route.TopHeadline.name,
            navController
        )
        SimpleButton(
            stringResource(id = R.string.screen_news_sources),
            Route.NewsBySource.name,
            navController
        )
        SimpleButton(
            stringResource(id = R.string.screen_news_countries),
            Route.NewsByCountries.name,
            navController
        )
        SimpleButton(
            stringResource(id = R.string.screen_news_languages),
            Route.NewsByLanguages.name,
            navController
        )
        SimpleButton(
            stringResource(id = R.string.screen_search_news),
            Route.NewsBySearch.name,
            navController
        )
        SimpleButton(
            stringResource(id = R.string.screen_offline_article),
            Route.OfflineArticle.name,
            navController
        )
    }
}

@Composable
fun SimpleButton(
    title: String,
    route: String,
    navController: NavController = rememberNavController()
) {

    Button(
        onClick = {
            navController.navigate(route = route)
        },
        shape = RoundedCornerShape(5.dp),
        elevation = ButtonDefaults.elevatedButtonElevation(),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        contentPadding = PaddingValues(10.dp)
    ) {
        Text(text = title, color = Color.White, fontSize = 16.sp)
    }
}

