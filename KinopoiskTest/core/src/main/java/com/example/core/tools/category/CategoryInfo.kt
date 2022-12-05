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
    SIMILAR("Похожий на"),
    STAFF("Над фильмом работали:"),
    ACTOR("В фильме снимались"),
    GALLERY("Галлерея"),
    DB("База Данных"),
    COLLECTION("Коллекции"),
    HISTORY("Вам было интересно"),
    LOOK("Просмотренно"),
}