package com.sanket.newsapp.ui.countries

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
import com.sanket.newsapp.data.model.Country
import com.sanket.newsapp.databinding.ActivityCountriesBinding
import com.sanket.newsapp.ui.base.BaseActivity
import com.sanket.newsapp.ui.base.UiState
import com.sanket.newsapp.ui.topheadline.TopHeadlineActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CountriesActivity : BaseActivity() {

    private lateinit var binding: ActivityCountriesBinding

    private lateinit var countriesViewModel: CountriesViewModel

    @Inject
    lateinit var countriesAdapter: CountriesAdapter

    companion object {
        fun startActivity(context: Context) {
            var intent = Intent(context, CountriesActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_countries)
        binding = ActivityCountriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setUI()
        setObserver()
    }

    private fun setupViewModel() {
        countriesViewModel = ViewModelProvider(this)[CountriesViewModel::class.java]
    }

    private fun setObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                countriesViewModel.uiState.collect {
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
                            Toast.makeText(this@CountriesActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun renderList(data: List<Country>) {

        logger.d(CountriesActivity::class.java, "Countries data:" + Gson().toJson(data))
        countriesAdapter.addData(data)
        countriesAdapter.notifyDataSetChanged()
    }

    private fun setUI() {
        var recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                this, (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = countriesAdapter
        countriesAdapter.itemClickListener = {
//            Toast.makeText(this, "Selected code is ${it.code}", Toast.LENGTH_SHORT).show()
            TopHeadlineActivity.startActivity(this, Constants.NewsType.COUNTRY(it.code))
        }
    }

}