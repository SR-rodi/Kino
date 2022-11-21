package com.example.feature_details.data.details_staff

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class StaffDetailsDTO(
    val films: ArrayList<Film>,
    val nameEn: String,
    val nameRu: String,
    val personId: Int,
    val posterUrl: String,
    val profession: String,
):Parcelable