package com.example.kinopoisk_api.domine.home_page.response

import com.example.kinopoisk_api.domine.home_page.films.PremieresDTO

data class ResponsePremieres(
    val items: List<PremieresDTO>,
    val total: Int
)