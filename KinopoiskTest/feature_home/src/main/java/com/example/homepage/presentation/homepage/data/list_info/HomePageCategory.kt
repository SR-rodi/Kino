package com.example.homepage.presentation.homepage.data.list_info

import android.os.Parcelable
import com.example.core.tools.all.NestedInfoInCategory
import com.example.core.tools.all.Query
import com.example.core.tools.base_model.category.BaseCategory
import com.example.core.tools.base_model.category.StartCategory
import com.example.core.tools.base_model.films.BaseFilm
import com.example.core.tools.category.CategoryInfo
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
class HomePageCategory(
    override val category: CategoryInfo,
    override val list: @RawValue List<NestedInfoInCategory>,
    override  val query: Query?=null
) : StartCategory(category, list),Parcelable