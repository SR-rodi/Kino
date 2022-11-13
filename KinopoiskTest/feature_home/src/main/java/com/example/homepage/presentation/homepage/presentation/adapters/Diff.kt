package com.example.homepage.presentation.homepage.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.homepage.presentation.homepage.data.films.BaseFilms

class Diff: DiffUtil.ItemCallback<BaseFilms> () {
    override fun areItemsTheSame(oldItem: BaseFilms, newItem: BaseFilms)=
        oldItem.filmId == newItem.filmId


    override fun areContentsTheSame(oldItem: BaseFilms, newItem: BaseFilms)=
        oldItem == newItem
}