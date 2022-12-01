package com.example.core.tools.category

import android.os.Parcelable
import com.example.core.tools.all.NestedInfoInCategory
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
enum class CategoryDetailsFilms (var itemId:Int,var itemList:@RawValue List<NestedInfoInCategory>):
    Parcelable {
    STAFF(0, emptyList()),
    GALLERY(0,emptyList()),
    FILMS(0,emptyList())
}
