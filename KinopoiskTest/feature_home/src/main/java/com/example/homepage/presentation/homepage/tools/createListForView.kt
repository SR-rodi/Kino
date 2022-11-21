package com.example.homepage.presentation.homepage.tools

import com.example.core.tools.all.CategoryFilms
import com.example.core.tools.general.Country
import com.example.core.tools.general.Genre


import com.example.core.tools.all.BaseFilms

fun List<BaseFilms>.createListForView(sizeListInView: Int): MutableList<BaseFilms> {

    val newList = mutableListOf<BaseFilms>()
    this.shuffled()
    val size = if (this.lastIndex < sizeListInView) this.size
    else sizeListInView
    for (i in 0 until  size) newList.add(this[i])
    return newList
}

fun CategoryFilms.createRandomCategory(counter: Country, genre: Genre): CategoryFilms {
    if (this == CategoryFilms.RANDOM) {
        this.apply {
           text = counter.info +" "+ genre.info
           query.counterID = counter.id
           query.genreId = genre.id
        }

    }
    return this
}

fun CategoryFilms.createPrimersCategory(years: Int, month: Int): CategoryFilms {
    if (this == CategoryFilms.PREMIERS){
        this.apply {
            query.year = years
            query.month = month.converterInMonth()
        }
    }
    return this
}

