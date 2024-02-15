package com.sanket.newsapp.ui.newssource

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sanket.newsapp.data.model.Source
import com.sanket.newsapp.databinding.NewsSourceItemLayoutBinding

class NewsSourcesAdapter(private val sources: ArrayList<Source>) :
    RecyclerView.Adapter<NewsSourcesAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: NewsSourceItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(source: Source) {
            binding.textViewSource.text = source.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            NewsSourceItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int {
        return sources.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {

        holder.bind(sources[position])
    }

    fun addData(list: List<Source>) {
        sources.addAll(list)
    }
}