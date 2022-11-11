package com.example.homepage.presentation.homepage.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.data.home_page.films.BaseFilms

class Diff: DiffUtil.ItemCallback<BaseFilms> () {
    override fun areItemsTheSame(oldItem: BaseFilms, newItem: BaseFilms)=
        oldItem.filmId == newItem.filmId


    override fun areContentsTheSame(oldItem: BaseFilms, newItem: BaseFilms)=
        oldItem == newItem
}