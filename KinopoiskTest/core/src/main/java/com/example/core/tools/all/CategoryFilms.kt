package com.example.core.tools.all

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class CategoryFilms(var text :String,val query: Query):Parcelable {
    BEST("ТОП-250", Query()),
    POPULAR("Популярное", Query()),
    PREMIERS("Премьеры", Query()),
    RANDOM("Исправить", Query())

}