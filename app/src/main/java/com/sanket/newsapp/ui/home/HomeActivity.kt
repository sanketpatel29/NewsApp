package com.sanket.newsapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sanket.newsapp.R
import com.sanket.newsapp.databinding.ActivityHomeBinding
import com.sanket.newsapp.ui.topheadline.TopHeadlineActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        binding.btnTopHeadlines.setOnClickListener {
            TopHeadlineActivity.startActivity(this)
        }
    }
}