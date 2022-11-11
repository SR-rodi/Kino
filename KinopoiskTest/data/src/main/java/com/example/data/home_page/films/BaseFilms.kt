package com.example.data.home_page.films

import com.example.data.home_page.films.general.Genre

interface BaseFilms{
    val nameRu: String
    val posterUrlPreview: String
    val genres: List<Genre>
    val rating :String?
    val filmId :Int
}