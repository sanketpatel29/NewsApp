package com.sanket.newsapp.ui.newssource

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
import com.sanket.newsapp.data.model.Source
import com.sanket.newsapp.databinding.ActivityNewsSourcesBinding
import com.sanket.newsapp.di.component.DaggerActivityComponent
import com.sanket.newsapp.di.module.ActivityModule
import com.sanket.newsapp.ui.base.BaseActivity
import com.sanket.newsapp.ui.base.UiState
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsSourcesActivity : BaseActivity() {

    @Inject
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
        injectDependencies()
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_news_sources)
        binding = ActivityNewsSourcesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpUI()
        setUpObserver()
    }

    private fun injectDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .activityModule(ActivityModule(this))
            .build()
            .inject(this)
    }

    private fun setUpUI() {

        var recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                this,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = sourcesAdapter
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