package com.example.core.tools

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.core.databinding.ItemFilmsBinding
import com.example.core.tools.all.BaseEntityFilm
import com.example.core.tools.all.BaseFilms

class FilmsAdapter(
    private val clickNextButton: () -> Unit,
    private val clickFilms: (filmID: Int) -> Unit
) : ListAdapter<BaseEntityFilm, FilmsViewHolder>(Diff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FilmsViewHolder(
        ItemFilmsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        val item = getItem(position)
        when (item.filmId){
            -1->{holder.bindNextShow { clickNextButton() }}
            -2->{holder.bindStun()}
            else -> {holder.bindItem(item) { clickFilms(it) }}
        }
    }
}