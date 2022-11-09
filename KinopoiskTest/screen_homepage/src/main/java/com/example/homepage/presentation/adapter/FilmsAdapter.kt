package com.example.homepage.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.core.databinding.ItemFilmsBinding
import com.example.kinopoisk_api.domine.home_page.HomePageItem
import com.example.homepage.presentation.viewholder.FilmsViewHolder

class FilmsAdapter(
    private val clickNextButton: () -> Unit,
    private val clickFilms: (filmName: String) -> Unit
) : ListAdapter<HomePageItem, FilmsViewHolder>(Diff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FilmsViewHolder(
        ItemFilmsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        if (position == itemCount - 1) holder.bindNextShow { clickNextButton() }
        else holder.bindItem(getItem(position)) { clickFilms(it) }
    }
}