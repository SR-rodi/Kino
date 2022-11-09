package com.example.kinopoisk_api.domine.home_page

import com.example.kinopoisk_api.domine.all.Genre

interface HomePageItem{

    val nameRu: String
    val posterUrlPreview: String
    val genres: List<Genre>
    val rating :String?
    val filmId :Int
}