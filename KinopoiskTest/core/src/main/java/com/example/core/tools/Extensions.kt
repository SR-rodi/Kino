package com.example.core.tools

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.core.R
import com.example.kinopoisk_api.CategoryFilms

import com.example.core.data.month.Month
import com.example.kinopoisk_api.domine.all.Country
import com.example.kinopoisk_api.domine.all.Genre
import com.example.kinopoisk_api.domine.home_page.CountryAndGenreDTO
import com.example.kinopoisk_api.domine.home_page.HomePageItem
import kotlin.random.Random
import kotlin.random.nextInt


fun ImageView.glide(imageUrl: String) {
    Glide.with(this)
        .load(imageUrl)
        .placeholder(R.drawable.ic_baseline_downloading_24)
        .error(R.drawable.ic_error)
        .into(this)
}

fun Int.converterInMonth(): String {
    var textMonth = ""
    if (this <= 0 || this > 12)
        textMonth = Month.AUGUST.name
    else
        Month.values().forEach { month ->
            if (this == month.count) textMonth = month.name
        }
    return textMonth
}

fun List<HomePageItem>.createListForView(sizeListInView: Int): MutableList<HomePageItem> {

    val newList = mutableListOf<HomePageItem>()
    this.shuffled()
    val size = if (this.lastIndex < sizeListInView) this.size
    else sizeListInView
    for (i in 0 until  size) newList.add(this[i])
    return newList
}

fun CountryAndGenreDTO.getRandomCounter()=
     countries[Random.nextInt(0..countries.lastIndex)]

fun CountryAndGenreDTO.getRandomGenre()
        = genres[Random.nextInt(0..genres.lastIndex)]

fun CategoryFilms.createRandomCategory(counter: Country, genre: Genre): CategoryFilms {
    if (this == CategoryFilms.RANDOM) this.text = counter.country +" "+ genre.genre
    return this
}