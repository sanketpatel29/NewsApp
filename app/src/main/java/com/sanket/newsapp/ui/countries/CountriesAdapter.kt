package com.sanket.newsapp.ui.countries

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sanket.newsapp.data.model.Country
import com.sanket.newsapp.databinding.CountryItemLayoutBinding

class CountriesAdapter(private val countries: ArrayList<Country>) :
    RecyclerView.Adapter<CountriesAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: CountryItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country) {
            binding.textViewCountry.text = country.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            CountryItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {

        holder.bind(countries[position])
    }

    fun addData(list: List<Country>) {
        countries.addAll(list)
    }
}