package com.example.core.tools

import com.example.core.R
import com.example.core.tools.all.NestedInfoInCategory
import com.example.core.tools.general.Genre


class NestedFilm(
    val nameRu: String?,
    val posterUrlPreview: String,
    val genres: List<Genre>,
    val rating: String?,
    val filmId: Int,
) : NestedInfoInCategory

