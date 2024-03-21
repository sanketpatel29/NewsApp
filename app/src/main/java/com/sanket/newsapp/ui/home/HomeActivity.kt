package com.sanket.newsapp.ui.home

import android.os.Bundle
import androidx.activity.compose.setContent
import com.sanket.newsapp.ui.base.BaseActivity
import com.sanket.newsapp.ui.base.NewsNavHost
import com.sanket.newsapp.ui.theme.NewsAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            NewsAppTheme {
                NewsNavHost()
            }

        }
    }
}