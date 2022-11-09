package com.example.homepage.presentation.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.kinopoisk_api.CategoryFilms
import com.example.homepage.data.HomePadeList
import com.example.homepage.databinding.ItemCategoryBinding
import com.example.homepage.presentation.adapter.FilmsAdapter

class CategoryViewHolder(private val binding: ItemCategoryBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindItem(
        item: HomePadeList,
        clickNextButton: (category: CategoryFilms) -> Unit,
        clickFilms: (filmName: String) -> Unit
    ) {
        val adapter = FilmsAdapter({ clickNextButton(item.category) }) { clickFilms(it) }
        if (item.filmList.isNotEmpty())
            adapter.submitList(item.filmList)
        binding.categoryName.text = item.category.text
        binding.filmsRecyclerView.adapter = adapter
    }
}