package com.example.homepage.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.kinopoisk_api.domine.home_page.HomePageItem

class Diff: DiffUtil.ItemCallback<HomePageItem> () {
    override fun areItemsTheSame(oldItem: HomePageItem, newItem: HomePageItem)=
        oldItem.filmId == newItem.filmId


    override fun areContentsTheSame(oldItem: HomePageItem, newItem: HomePageItem)=
        oldItem == newItem
}