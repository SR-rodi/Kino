package com.example.homepage.presentation.homepage.presentation.adapters.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.core.databinding.ItemCategoryBinding
import com.example.core.tools.category.CategoryInfo
import com.example.homepage.presentation.homepage.data.list_info.HomePadeList
import com.example.core.tools.adapter.oldversionsadapter.FilmsAdapter

class CategoryViewHolder(private val binding: ItemCategoryBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindItem(
        item: HomePadeList,
        clickNextButton: (category: CategoryInfo) -> Unit,
        clickFilms: (filmID: Int) -> Unit
    ) {
        val adapter = FilmsAdapter({ clickNextButton(item.category.category) }) { clickFilms(it) }
        if (item.filmList.isNotEmpty())
            adapter.submitList(item.filmList)
        binding.categoryName.text = item.category.category.text
        binding.filmsRecyclerView.adapter = adapter
        binding.buttonAll.setOnClickListener { clickNextButton(item.category.category) }
    }
}