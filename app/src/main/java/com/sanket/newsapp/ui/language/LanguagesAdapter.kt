package com.sanket.newsapp.ui.language

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sanket.newsapp.data.model.Language
import com.sanket.newsapp.databinding.LanguageItemLayoutBinding

class LanguagesAdapter(private val languages: ArrayList<Language>) :
    RecyclerView.Adapter<LanguagesAdapter.DataViewHolder>() {

    class DataViewHolder(private val binding: LanguageItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(language: Language) {

            binding.textViewLanguage.text = language.name
            binding.textViewLanguage.isChecked = language.isChecked
            binding.textViewLanguage.setOnClickListener {

                language.isChecked = binding.textViewLanguage.isChecked
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        return DataViewHolder(
            LanguageItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )

    }

    override fun getItemCount(): Int {
        return languages.size
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {

        holder.bind(languages[position])

    }

    fun addData(list: List<Language>) {
        languages.addAll(list)
    }

    fun getSelectedLanguages(): List<Language> {
        return languages.filter { it.isChecked }
    }
}