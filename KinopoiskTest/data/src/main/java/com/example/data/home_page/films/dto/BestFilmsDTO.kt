package com.example.data.home_page.films.dto

import com.example.data.home_page.films.BaseFilms
import com.example.data.home_page.films.general.Genre

data class BestFilmsDTO(
    override val posterUrlPreview: String,
    override val rating: String? = null,
    override val nameRu: String,
    override val genres: List<Genre>,
    override val filmId: Int,
): BaseFilms