package com.example.core.tools.all

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
enum class CategoryInfo(var itemId:Int,var itemList:@RawValue List<NestedInfoInCategory>):Parcelable{
    STAFF(0, emptyList()),GALLERY(0,emptyList()),FILMS(0,emptyList())
}