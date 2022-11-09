package com.example.kinopoisk_api.domine.home_page.response

import com.example.kinopoisk_api.domine.home_page.films.FilmsDTO

data class ResponseFilms(
    val films: List<FilmsDTO>,
    val pagesCount: Int
)