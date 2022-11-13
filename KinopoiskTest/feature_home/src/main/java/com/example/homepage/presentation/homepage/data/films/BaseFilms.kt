package com.example.homepage.presentation.homepage.data.films

import com.example.core.tools.general.Genre

interface BaseFilms{
    val nameRu: String
    val posterUrlPreview: String
    val genres: List<Genre>
    val rating :String?
    val filmId :Int
}