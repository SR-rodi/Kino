package com.example.core.tools.category

import android.os.Parcelable
import com.example.core.tools.all.Query
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class CategoryInfo(var text :String, val query: Query): Parcelable {
    BEST("ТОП-250", Query()),
    POPULAR("Популярное", Query()),
    PREMIERS("Премьеры", Query()),
    RANDOM("Исправить", Query()),
    STUFF("Персанал",Query()),
    SIMULA("Похожий на",Query()),
}