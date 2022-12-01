package com.example.core.tools.category

import android.os.Parcelable
import com.example.core.tools.all.Query
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class CategoryInfo(var text :String, ): Parcelable {
    BEST("ТОП-250"),
    POPULAR("Популярное"),
    PREMIERS("Премьеры"),
    RANDOM("Исправить"),
    SIMULA("Похожий на"),
    STAFF("Персонал"),
    GALLERY("Галлерея"),
    FILMS("Похожие")
}