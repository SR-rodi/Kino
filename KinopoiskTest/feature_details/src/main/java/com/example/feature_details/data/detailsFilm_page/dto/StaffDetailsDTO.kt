package com.example.feature_details.data.detailsFilm_page.dto

import android.os.Parcelable
import com.example.feature_details.data.details_staff.Film
import kotlinx.android.parcel.Parcelize

class StaffDetailsDTO(
    val films: ArrayList<Film>,
    val nameEn: String,
    val nameRu: String,
    val personId: Int,
    val posterUrl: String,
    val profession: String,
)