package com.example.core.tools.adapter.diffutil

import androidx.recyclerview.widget.DiffUtil
import com.example.core.tools.all.NestedInfoInCategory
import com.example.core.tools.base_model.films.BaseFilm

class BaseFilmsDiffUtil:DiffUtil.ItemCallback<NestedInfoInCategory>() {
    override fun areItemsTheSame(oldItem: NestedInfoInCategory, newItem: NestedInfoInCategory) = oldItem == newItem

    override fun areContentsTheSame(oldItem: NestedInfoInCategory, newItem: NestedInfoInCategory)= oldItem == newItem
}