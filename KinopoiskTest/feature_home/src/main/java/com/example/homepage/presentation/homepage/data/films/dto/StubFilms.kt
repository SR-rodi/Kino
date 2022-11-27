package com.example.homepage.presentation.homepage.data.films.dto

import com.example.core.tools.all.BaseEntityFilm
import com.example.core.tools.general.Genre

class StubFilms(
    override val posterUrlPreview: String = "",
    override val rating: String? = null,
    override val nameRu: String="",
    override val genres: List<Genre> = emptyList(),
    override val filmId: Int=-1,
) : BaseEntityFilm()