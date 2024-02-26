package com.sanket.newsapp.ui.language

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
import com.google.gson.Gson
import com.sanket.newsapp.R
import com.sanket.newsapp.apputils.Constants
import com.sanket.newsapp.data.model.Language
import com.sanket.newsapp.databinding.ActivityLanguagesBinding
import com.sanket.newsapp.ui.base.BaseActivity
import com.sanket.newsapp.ui.base.UiState
import com.sanket.newsapp.ui.topheadline.TopHeadlineActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LanguagesActivity : BaseActivity() {

    private lateinit var binding: ActivityLanguagesBinding

    private lateinit var languageViewModel: LanguageViewModel

    @Inject
    lateinit var languagesAdapter: LanguagesAdapter

    companion object {
        fun startActivity(context: Context) {
            var intent = Intent(context, LanguagesActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_languages)
        binding = ActivityLanguagesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setUI()
        setObserver()
    }


    private fun setupViewModel() {
        languageViewModel = ViewModelProvider(this)[LanguageViewModel::class.java]
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
        logger.d(LanguagesActivity::class.java, "Languages data:" + Gson().toJson(data))
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

        binding.btnFetchNewsByLanguages.setOnClickListener {

            if (languagesAdapter.getSelectedLanguages().size > 2) {
                Toast.makeText(
                    this,
                    "Max 2 languages selection is allowed only",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            var selectedLanguages = languagesAdapter.getSelectedLanguages().joinToString { it.code }
            logger.d(LanguagesActivity.javaClass, "Selected languages= $selectedLanguages")

            TopHeadlineActivity.startActivity(this, Constants.NewsType.LANGUAGE(selectedLanguages))
        }
    }

}