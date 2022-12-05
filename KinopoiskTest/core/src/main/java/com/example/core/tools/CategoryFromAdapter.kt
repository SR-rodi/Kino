package com.example.core.tools

import com.example.core.tools.all.NestedInfoInCategory
import com.example.core.tools.base_model.category.BaseCategory
import com.example.core.tools.category.CategoryInfo
import com.example.core.tools.general.InfoFilms

class CategoryFromAdapter(
    override val category: CategoryInfo,
    val listValue: List<NestedInfoInCategory>,
) : BaseCategory