package com.example.core.tools

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.core.tools.all.BaseFilms

class Diff: DiffUtil.ItemCallback<BaseFilms> () {
    override fun areItemsTheSame(oldItem: BaseFilms, newItem: BaseFilms)=
        oldItem.filmId == newItem.filmId


    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: BaseFilms, newItem: BaseFilms)=
        oldItem == newItem
}