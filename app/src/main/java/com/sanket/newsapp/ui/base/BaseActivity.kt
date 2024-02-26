package com.sanket.newsapp.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sanket.newsapp.apputils.logger.Logger
import javax.inject.Inject

open class BaseActivity : AppCompatActivity() {
    @Inject
    lateinit var logger: Logger
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}