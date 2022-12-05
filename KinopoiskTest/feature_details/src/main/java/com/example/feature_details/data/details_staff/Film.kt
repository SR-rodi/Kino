package com.example.feature_details.data.details_staff

import android.os.Parcelable
import com.example.core.tools.base_model.films.FilmographyMove
import kotlinx.android.parcel.Parcelize

@Parcelize
class Film(
    val description: String?,
    val filmId: Int,
    val general: Boolean,
    var nameEn: String?,
    var nameRu: String?,
    val professionKey: String,
    val rating: String?,
    var posterURL: String? = null,
    var isExpand: Boolean = true
) : Parcelable {
    fun toFilmView()=FilmView(description, filmId, general, nameEn, nameRu, professionKey, rating, posterURL)
}

class FilmView(
   override val description: String?,
   override val filmId: Int,
   override val general: Boolean,
   override var nameEn: String?,
   override var nameRu: String?,
   override val professionKey: String,
   override val rating: String?,
   override var posterURL: String? = null,
   override var isExpand: Boolean = false
) :FilmographyMove(description, filmId, general, nameEn, nameRu, professionKey, rating, posterURL)