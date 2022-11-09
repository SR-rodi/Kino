package com.example.kinopoisk_api.domine.home_page

data class ResponseFiltresFilms(
    val items: List<Item>,
    val total: Int,
    val totalPages: Int
)