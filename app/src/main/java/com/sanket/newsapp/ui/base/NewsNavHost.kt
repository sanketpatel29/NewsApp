package com.sanket.newsapp.ui.base

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sanket.newsapp.apputils.Constants
import com.sanket.newsapp.ui.countries.CountriesScreenRoute
import com.sanket.newsapp.ui.home.HomeScreenRoute
import com.sanket.newsapp.ui.language.LanguagesScreenRoute
import com.sanket.newsapp.ui.newssource.SourcesScreenRoute
import com.sanket.newsapp.ui.search.SearchNewsScreenRoute
import com.sanket.newsapp.ui.topheadline.TopHeadLineRoute

sealed class Route(val name: String) {
    object Home : Route("Home")

    object TopHeadline : Route("topheadline?newsType={newsType}&newsTypeId={newsTypeId}") {
        fun buildPath(
            newsType: String = "",
            newsTypeId: String = "",
        ): String {
            return "topheadline?newsType=$newsType&newsTypeId=$newsTypeId"
        }
    }

    object NewsBySource : Route("newsbysource")
    object NewsByCountries : Route("newsbycountries")
    object NewsByLanguages : Route("newsbylanguages")
    object NewsBySearch : Route("newsbysearch")
}

@Preview
@Composable
fun NewsNavHost() {
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Route.Home.name
    ) {
        composable(route = Route.Home.name) {
            HomeScreenRoute(navController)
        }
        composable(
            route = Route.TopHeadline.name,
            arguments = listOf(
                navArgument(Constants.NewsBy.IntentParam.Key.NEWS_TYPE) {
                    type = NavType.StringType
                    defaultValue = ""
                },
                navArgument(Constants.NewsBy.IntentParam.Key.NEWS_TYPE_ID) {
                    type = NavType.StringType
                    defaultValue = ""
                }
            )
        ) {

            val newsType = it.arguments?.getString(Constants.NewsBy.IntentParam.Key.NEWS_TYPE)
            val newsTypeId =
                it.arguments?.getString(Constants.NewsBy.IntentParam.Key.NEWS_TYPE_ID)

            TopHeadLineRoute(
                onNewsClick = {
                    openCustomChromeTab(context = context, it)
                },
                newsType = newsType ?: "",
                newsIdentifier = newsTypeId ?: ""
            )
        }
        composable(route = Route.NewsBySource.name) {
            SourcesScreenRoute(onNewsClick = {
                navController.navigate(
                    route = Route.TopHeadline.buildPath(
                        newsType = Constants.NewsBy.IntentParam.Value.SOURCE,
                        newsTypeId = it
                    )
                )
            })
        }
        composable(route = Route.NewsByCountries.name) {
            CountriesScreenRoute(onNewsClick = {
                navController.navigate(
                    route = Route.TopHeadline.buildPath(
                        newsType = Constants.NewsBy.IntentParam.Value.COUNTRY,
                        newsTypeId = it
                    )
                )
            })
        }
        composable(route = Route.NewsByLanguages.name) {
            LanguagesScreenRoute(onNewsClick = {
                navController.navigate(
                    route = Route.TopHeadline.buildPath(
                        newsType = Constants.NewsBy.IntentParam.Value.LANGUAGE,
                        newsTypeId = it
                    )
                )
            })
        }
        composable(route = Route.NewsBySearch.name) {
            SearchNewsScreenRoute(onNewsClick = {
                openCustomChromeTab(context = context, it)
            })
        }
    }
}

fun openCustomChromeTab(context: Context, url: String) {
    val builder = CustomTabsIntent.Builder()
    val customTabsIntent = builder.build()
    customTabsIntent.launchUrl(context, Uri.parse(url))
}
