package com.example.data.home_page.films.dto

import com.example.data.home_page.films.BaseFilms
import com.example.data.home_page.films.general.Genre

data class PopularDTO(
    override val filmId: Int,
    override val nameRu: String,
    override val genres: List<Genre>,
    override val posterUrlPreview: String,
    override val rating: String? = null,
) : BaseFilms