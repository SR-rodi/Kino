package com.example.homepage.presentation.homepage.presentation.adapters.home_page

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.core.databinding.ItemCategoryBinding
import com.example.core.tools.category.CategoryInfo
import com.example.homepage.presentation.homepage.data.list_info.HomePadeList

import com.example.homepage.presentation.homepage.presentation.adapters.viewholder.CategoryViewHolder

class CategoryAdapter(
    private val category: List<HomePadeList>,
    private val clickNextButton: (category: CategoryInfo) -> Unit,
    private val clickFilms: (filmID: Int) -> Unit
) :
    RecyclerView.Adapter<CategoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CategoryViewHolder(
        ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {

        holder.bindItem( category[position], { clickNextButton(it) }, { clickFilms(it) })
    }

    override fun getItemCount() = category.size
}


