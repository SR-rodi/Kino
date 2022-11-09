package com.example.homepage.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kinopoisk_api.CategoryFilms
import com.example.homepage.data.HomePadeList
import com.example.homepage.databinding.ItemCategoryBinding
import com.example.homepage.presentation.viewholder.CategoryViewHolder

class CategoryAdapter(
    private val category: List<HomePadeList>,
    private val clickNextButton: (category: CategoryFilms) -> Unit,
    private val clickFilms: (filmName: String) -> Unit
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


