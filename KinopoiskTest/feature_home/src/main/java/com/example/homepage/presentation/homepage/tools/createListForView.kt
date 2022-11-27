package com.example.homepage.presentation.homepage.tools

import com.example.core.tools.all.BaseEntityFilm
import com.example.core.tools.all.CategoryFilms
import com.example.core.tools.general.Country
import com.example.core.tools.general.Genre


import com.example.homepage.presentation.homepage.data.films.dto.StubFilms

fun List<BaseEntityFilm>.createListForView(
    sizeListInView: Int,
): List<BaseEntityFilm> {

    val size = if (this.lastIndex < sizeListInView) this.size
    else sizeListInView

    val list = this.take(size).toMutableList()

    if (list.isNotEmpty()) list.add(StubFilms())
    else list.add(StubFilms(filmId = -2))
    return list
}

fun CategoryFilms.createRandomCategory(counter: Country, genre: Genre): CategoryFilms {
    if (this == CategoryFilms.RANDOM) {
        this.apply {
            text = counter.info + " " + genre.info
            query.counterID = counter.id
            query.genreId = genre.id
        }

    }
    return this
}

fun CategoryFilms.createPrimersCategory(years: Int, month: Int): CategoryFilms {
    if (this == CategoryFilms.PREMIERS) {
        this.apply {
            query.year = years
            query.month = month.converterInMonth()
        }
    }
    return this
}

