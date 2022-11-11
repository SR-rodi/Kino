package com.example.core.tools.extensions

import com.example.data.home_page.all.CategoryFilms
import com.example.data.home_page.films.general.Country
import com.example.data.home_page.films.general.Genre
import com.example.data.home_page.films.general.GenreCountry
import com.example.data.home_page.films.BaseFilms

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

fun List<GenreCountry>.createName(): String {
    var genreName = ""
    this.forEachIndexed { index, info ->
        genreName += if (index == this.lastIndex) info.info
        else "${info.info}, "
    }
    return genreName
}
