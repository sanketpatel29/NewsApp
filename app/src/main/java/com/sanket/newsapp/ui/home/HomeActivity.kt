package com.sanket.newsapp.ui.home

import android.os.Bundle
import com.sanket.newsapp.R
import com.sanket.newsapp.databinding.ActivityHomeBinding
import com.sanket.newsapp.ui.base.BaseActivity
import com.sanket.newsapp.ui.countries.CountriesActivity
import com.sanket.newsapp.ui.language.LanguagesActivity
import com.sanket.newsapp.ui.newssource.NewsSourcesActivity
import com.sanket.newsapp.ui.topheadline.TopHeadlineActivity

class HomeActivity : BaseActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnTopHeadlines.setOnClickListener {
            TopHeadlineActivity.startActivity(this)
        }

        binding.btnNewsSources.setOnClickListener {
            NewsSourcesActivity.startActivity(this)
        }

        binding.btnCountries.setOnClickListener {
            CountriesActivity.startActivity(this)
        }

        binding.btnLanguages.setOnClickListener {
            LanguagesActivity.startActivity(this)
        }
    }
}