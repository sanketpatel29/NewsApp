package com.sanket.newsapp.ui.countries

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
import com.sanket.newsapp.data.model.Country
import com.sanket.newsapp.ui.base.ShowError
import com.sanket.newsapp.ui.base.ShowLoading
import com.sanket.newsapp.ui.base.TopAppBar
import com.sanket.newsapp.ui.base.UiState

@Composable
fun CountriesScreenRoute(
    onNewsClick: (countryCode: String) -> Unit,
    countriesViewModel: CountriesViewModel = hiltViewModel(),
    navController: NavController
) {

    val countriesUiState: UiState<List<Country>> by countriesViewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(topBar = {
        TopAppBar(
            title = stringResource(id = R.string.screen_news_countries), showBackArrow = true
        ) { navController.popBackStack() }
    }, content = { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CountriesScreen(countriesUiState, onNewsClick)
        }
    })


}

@Composable
fun CountriesScreen(
    countriesUiState: UiState<List<Country>>,
    onNewsClick: (sourceId: String) -> Unit
) {
    when (countriesUiState) {
        is UiState.Error -> {
            ShowError(text = countriesUiState.message)
        }

        is UiState.Loading -> {
            ShowLoading()
        }

        is UiState.Success -> {
            ShowCountryList(countriesUiState.data, onNewsClick)
        }
    }

}

@Composable
fun ShowCountryList(data: List<Country>, onNewsClick: (sourceId: String) -> Unit) {
    LazyColumn {
        items(data.size) { index -> ShowCountry(data[index], onNewsClick) }
    }
}

@Composable
fun ShowCountry(country: Country, onNewsClick: (sourceId: String) -> Unit) {
    Button(
        onClick = { country.code.let(onNewsClick) },
        shape = RoundedCornerShape(5.dp),
        modifier = Modifier
            .padding(start = 20.dp, top = 5.dp, end = 20.dp, bottom = 5.dp)
            .fillMaxWidth(),
        contentPadding = PaddingValues(16.dp)
    ) {
        Text(
            text = country.name,
            color = Color.White,
            fontSize = 16.sp
        )
    }
}
