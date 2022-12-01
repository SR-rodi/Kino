package com.example.homepage.presentation.homepage.presentation.adapters.filmsAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.core.databinding.ItemFilmsBinding
import com.example.core.tools.adapter.diffutil.CategoryDiffUtil
import com.example.core.tools.adapter.oldversionsadapter.BaseFilmsDiff
import com.example.core.tools.adapter.oldversionsadapter.FilmsViewHolder
import com.example.core.tools.base_model.films.BaseFilm

class FilmAdapterFromPage(
   private val onClickItem:(filmID:Int)->Unit) :PagingDataAdapter<BaseFilm, FilmsViewHolder>(
    BaseFilmsDiff()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FilmsViewHolder(
        ItemFilmsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {

        getItem(position)?.let { holder.bindItem(it,true){id-> onClickItem(id) } }
    }
}