package com.example.homepage.presentation.homepage.adapters.categorypage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.core.databinding.ItemFilmsBinding
import com.example.homepage.presentation.homepage.adapters.Diff
import com.example.homepage.presentation.homepage.viewholder.FilmsViewHolder
import com.example.data.home_page.films.BaseFilms

class FilmAdapterFromPage :PagingDataAdapter<BaseFilms, FilmsViewHolder>(Diff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FilmsViewHolder(
        ItemFilmsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        getItem(position)?.let { holder.bindItem(it,true){} }
    }


}