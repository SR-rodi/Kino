package com.example.core.tools

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.core.tools.all.BaseEntityFilm
import com.example.core.tools.all.BaseFilms

class Diff: DiffUtil.ItemCallback<BaseEntityFilm> () {
    override fun areItemsTheSame(oldItem: BaseEntityFilm, newItem: BaseEntityFilm)=
        oldItem.filmId == newItem.filmId


    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: BaseEntityFilm, newItem: BaseEntityFilm)=
        oldItem == newItem
}