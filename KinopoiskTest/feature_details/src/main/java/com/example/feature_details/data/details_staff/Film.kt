package com.example.feature_details.data.details_staff

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class Film(
    val description: String?,
    val filmId: Int,
    val general: Boolean,
    var nameEn: String?,
    var nameRu: String?,
    val professionKey: String,
    val rating: String,
    var posterURL: String? = null,
    var isExpand: Boolean = true
) : Parcelable