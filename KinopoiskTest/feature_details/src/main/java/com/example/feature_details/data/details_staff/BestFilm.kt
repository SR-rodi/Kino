package com.example.feature_details.data.details_staff

import com.example.core.tools.all.BaseEntityFilm
import com.example.core.tools.all.BaseFilms
import com.example.core.tools.general.Genre

class BestFilm(
    override val nameRu: String,
    override val posterUrlPreview: String,
    override val genres: List<Genre>,
    override val rating: String?,
    override val filmId: Int,
) : BaseEntityFilm()