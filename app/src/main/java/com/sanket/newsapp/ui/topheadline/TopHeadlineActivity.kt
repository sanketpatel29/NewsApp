package com.sanket.newsapp.ui.topheadline

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sanket.newsapp.NewsApplication
import com.sanket.newsapp.R
import com.sanket.newsapp.apputils.Constants
import com.sanket.newsapp.data.model.Article
import com.sanket.newsapp.databinding.ActivityTopHeadlineBinding
import com.sanket.newsapp.di.component.DaggerActivityComponent
import com.sanket.newsapp.di.module.ActivityModule
import com.sanket.newsapp.ui.base.BaseActivity
import com.sanket.newsapp.ui.base.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class TopHeadlineActivity : BaseActivity() {

    @Inject
    lateinit var newsListViewModel: TopHeadlineViewModel

    @Inject
    lateinit var adapter: TopHeadlineAdapter

    private lateinit var binding: ActivityTopHeadlineBinding

    companion object {
        private const val NEWS_BY = "NEWS_BY"
        fun startActivity(context: Context) {
            var intent = Intent(context, TopHeadlineActivity::class.java)
            context.startActivity(intent)
        }

        fun startActivity(context: Context, newsType: Constants.NewsType) {
            var intent = Intent(context, TopHeadlineActivity::class.java)
            intent.putExtra(NEWS_BY, newsType)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        injectDependencies()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_top_headline)

        binding = ActivityTopHeadlineBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initData()
        setupUI()
        setupObserver()
    }

    private fun initData() {
        val newsType: Constants.NewsType? = intent.getParcelableExtra(NEWS_BY)

        newsType.let {
            when (newsType) {
                is Constants.NewsType.COUNTRY -> {
                    newsListViewModel.fetchNewsByCountry(newsType.countryCode)
                    Toast.makeText(
                        this, "Selected code is ${newsType.countryCode}", Toast.LENGTH_SHORT
                    ).show()
                }

                is Constants.NewsType.SOURCE -> {
                    newsListViewModel.fetchNewsBySource(newsType.sounrceId)
                    Toast.makeText(
                        this, "Selected source id is ${newsType.sounrceId}", Toast.LENGTH_SHORT
                    ).show()

                }

                is Constants.NewsType.LANGUAGE -> {
                    newsListViewModel.fetchNewsByLanguage(newsType.languageId)
                    Toast.makeText(
                        this, "Selected language is ${newsType.languageId}", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        if (newsType == null) {
            newsListViewModel.fetchNews()
        }
    }

    private fun setupUI() {

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )
        binding.recyclerView.adapter = adapter

    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                newsListViewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            renderList(it.data)
                            binding.recyclerView.visibility = View.VISIBLE
                        }

                        is UiState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                        }

                        is UiState.Error -> {
                            //Handle Error
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this@TopHeadlineActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun renderList(articleList: List<Article>) {
        adapter.addData(articleList)
        adapter.notifyDataSetChanged()
    }


    private fun injectDependencies() {

        DaggerActivityComponent.builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)
    }
}