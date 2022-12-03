package com.example.core.tools.adapter.oldversionsadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.core.databinding.ItemFilmsBinding
import com.example.core.tools.base_model.films.BaseFilm

class FilmsAdapter(
    private val clickNextButton: () -> Unit,
    private val clickFilms: (filmID: Int) -> Unit
) : ListAdapter<BaseFilm, FilmsViewHolder>(BaseFilmsDiff()) {


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
class BaseFilmsDiff: DiffUtil.ItemCallback<BaseFilm>() {
    override fun areItemsTheSame(oldItem: BaseFilm, newItem: BaseFilm) = oldItem == newItem

    override fun areContentsTheSame(oldItem: BaseFilm, newItem: BaseFilm)= oldItem == newItem
}

