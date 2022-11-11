package com.example.homepage.presentation.homepage.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.data.home_page.all.CategoryFilms
import com.example.data.home_page.list_info.HomePadeList
import com.example.homepage.databinding.ItemCategoryBinding
import com.example.homepage.presentation.homepage.adapters.home_page.FilmsAdapter

class CategoryViewHolder(private val binding: ItemCategoryBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindItem(
        item: HomePadeList,
        clickNextButton: (category: CategoryFilms) -> Unit,
        clickFilms: (filmID: Int) -> Unit
    ) {
        val adapter = FilmsAdapter({ clickNextButton(item.category) }) { clickFilms(it) }
        if (item.filmList.isNotEmpty())
            adapter.submitList(item.filmList)
        binding.categoryName.text = item.category.text
        binding.filmsRecyclerView.adapter = adapter
        binding.buttonAll.setOnClickListener { clickNextButton(item.category) }
    }
}