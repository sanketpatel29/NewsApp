package com.sanket.newsapp.ui.language

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
import com.google.gson.Gson
import com.sanket.newsapp.NewsApplication
import com.sanket.newsapp.R
import com.sanket.newsapp.apputils.Constants
import com.sanket.newsapp.apputils.Logger
import com.sanket.newsapp.data.model.Language
import com.sanket.newsapp.databinding.ActivityCountriesBinding
import com.sanket.newsapp.di.component.DaggerActivityComponent
import com.sanket.newsapp.di.module.ActivityModule
import com.sanket.newsapp.ui.base.BaseActivity
import com.sanket.newsapp.ui.base.UiState
import com.sanket.newsapp.ui.topheadline.TopHeadlineActivity
import kotlinx.coroutines.launch
import javax.inject.Inject

class LanguagesActivity : BaseActivity() {

    private lateinit var binding: ActivityCountriesBinding

    @Inject
    lateinit var languageViewModel: LanguageViewModel

    @Inject
    lateinit var languagesAdapter: LanguagesAdapter

    companion object {
        fun startActivity(context: Context) {
            var intent = Intent(context, LanguagesActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        initDependencies()
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_languages)
        binding = ActivityCountriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUI()
        setObserver()
    }

    private fun setObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                languageViewModel.uiState.collect {
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
                            Toast.makeText(this@LanguagesActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun renderList(data: List<Language>) {
        Logger().d(LanguagesActivity::class.java, "Languages data:" + Gson().toJson(data))
        languagesAdapter.addData(data)
        languagesAdapter.notifyDataSetChanged()
    }

    private fun setUI() {
        var recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                this, (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = languagesAdapter
        languagesAdapter.onItemClickListener = {
            TopHeadlineActivity.startActivity(this, Constants.NewsType.LANGUAGE(it.code))
        }
    }

    private fun initDependencies() {
        DaggerActivityComponent.builder()
            .applicationComponent((application as NewsApplication).applicationComponent)
            .activityModule(ActivityModule(this)).build().inject(this)
    }

}