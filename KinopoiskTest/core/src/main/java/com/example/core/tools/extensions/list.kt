package com.example.core.tools.extensions

import com.example.core.tools.base_model.films.BaseEntityFilm
import com.example.core.tools.base_model.films.BaseFilm

fun List<BaseFilm>.createListForView(
    sizeListInView: Int,
): List<BaseFilm> {

    val size = if (this.lastIndex < sizeListInView) this.size
    else sizeListInView

    val list = this.take(size).toMutableList()

    if (list.isNotEmpty()) list.add(BaseFilm())
    else list.add(BaseFilm(filmId = -2))
    return list
}

fun List<BaseEntityFilm>.toBaseFilmList(): List<BaseFilm> {
    val list= mutableListOf<BaseFilm>()
    this.forEach { list.add(it.toBaseFilm()) }
    return list
}

