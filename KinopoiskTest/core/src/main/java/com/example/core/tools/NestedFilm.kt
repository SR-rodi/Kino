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

class NestedCollection(
    val id: Int? = null,
    val nameCollection: String,
    var size: Int = 0,
    var icon:Int = R.drawable.ic_profile,
    var isDelete:Boolean = true
):NestedInfoInCategory