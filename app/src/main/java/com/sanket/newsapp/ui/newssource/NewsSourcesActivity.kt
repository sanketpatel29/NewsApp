package com.sanket.newsapp.ui.newssource

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sanket.newsapp.R
import com.sanket.newsapp.apputils.Constants
import com.sanket.newsapp.data.model.Source
import com.sanket.newsapp.databinding.ActivityNewsSourcesBinding
import com.sanket.newsapp.ui.base.BaseActivity
import com.sanket.newsapp.ui.base.UiState
import com.sanket.newsapp.ui.topheadline.TopHeadlineActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class NewsSourcesActivity : BaseActivity() {

    lateinit var newsSourceViewModel: NewsSourceViewModel

    @Inject
    lateinit var sourcesAdapter: NewsSourcesAdapter

    private lateinit var binding: ActivityNewsSourcesBinding

    companion object {
        fun startActivity(context: Context) {
            var intent = Intent(context, NewsSourcesActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_news_sources)
        binding = ActivityNewsSourcesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setUpUI()
        setUpObserver()
    }

    private fun setupViewModel() {
        newsSourceViewModel = ViewModelProvider(this)[NewsSourceViewModel::class.java]
    }

    private fun setUpUI() {

        var recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                this, (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = sourcesAdapter

        sourcesAdapter.onItemClickListener = {
            TopHeadlineActivity.startActivity(this, Constants.NewsType.SOURCE(it.id))
        }
    }

    private fun setUpObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                newsSourceViewModel.uiState.collect {
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
                            Toast.makeText(this@NewsSourcesActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun renderList(data: List<Source>) {
        sourcesAdapter.addData(data)
        sourcesAdapter.notifyDataSetChanged()
    }
}