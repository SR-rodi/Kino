package com.example.core.tools

import com.example.core.tools.all.NestedInfoInCategory
import com.example.core.tools.general.InfoFilms

class CategoryFromAdapter(
    val category: String,
    val listValue: List<NestedInfoInCategory>,
    var isActor :Boolean = false
) : InfoFilms