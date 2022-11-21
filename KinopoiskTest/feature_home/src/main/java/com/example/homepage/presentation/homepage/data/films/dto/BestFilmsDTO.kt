package com.example.homepage.presentation.homepage.data.films.dto

import com.example.core.tools.general.Genre
import com.example.core.tools.all.BaseFilms


data class BestFilmsDTO(
    override val posterUrlPreview: String,
    override val rating: String? = null,
    override val nameRu: String,
    override val genres: List<Genre>,
    override val filmId: Int,
): BaseFilms