package com.example.kinopoisk_api

import android.os.Parcelable
import com.example.kinopoisk_api.domine.repo.Query
import kotlinx.android.parcel.Parcelize


enum class CategoryFilms(var text :String,val query: Query) {
    BEST("ТОП-250", Query()),
    POPULAR("Популярное",Query()),
    PREMIERS("Премьеры",Query()),
    RANDOM("Исправить",Query())

}