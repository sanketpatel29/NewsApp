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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.sanket.newsapp.ui.base.Route

@Preview
@Composable
fun HomeScreenRoute(navController: NavController = rememberNavController()) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HomeScreen(navController)
    }
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
        SimpleButton("Top Headlines", Route.TopHeadline.name, navController)
        SimpleButton("News Sources", Route.NewsBySource.name, navController)
        SimpleButton("News Countries ", Route.NewsByCountries.name, navController)
        SimpleButton("Languages", Route.NewsByLanguages.name, navController)
        SimpleButton("Search", Route.NewsBySearch.name, navController)
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

