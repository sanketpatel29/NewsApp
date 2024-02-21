package com.sanket.newsapp.ui.language

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sanket.newsapp.apputils.OnItemClickListener
import com.sanket.newsapp.data.model.Language
import com.sanket.newsapp.databinding.NewsSourceItemLayoutBinding

class LanguagesAdapter(private val languages: ArrayList<Language>) :
    RecyclerView.Adapter<LanguagesAdapter.DataViewHolder>() {

    lateinit var onItemClickListener: OnItemClickListener<Language>

    class DataViewHolder(private val binding: NewsSourceItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(language: Language, onItemClickListener: OnItemClickListener<Language>) {
            binding.textViewSource.text = language.name
            binding.textViewSource.setOnClickListener {
                onItemClickListener(language)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            NewsSourceItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    }

    override fun getItemCount(): Int {
        return languages.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {

        holder.bind(languages[position], onItemClickListener)
    }

    fun addData(list: List<Language>) {
        languages.addAll(list)
    }
}