package com.example.core.tools.adapter

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.core.tools.base_model.films.BaseFilm

class Diff: DiffUtil.ItemCallback<BaseFilm>() {
    override fun areItemsTheSame(oldItem: BaseFilm, newItem: BaseFilm)=
        oldItem.filmId == newItem.filmId


    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: BaseFilm, newItem: BaseFilm)=
        oldItem == newItem
}