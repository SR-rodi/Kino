package com.example.feature_details.details_staff

data class StaffDetailsDTO(
    val age: Int,
    val birthday: String,
    val birthplace: String,
    val death: Any,
    val deathplace: Any,
    val facts: List<String>,
    val films: List<Film>,
    val growth: Int,
    val hasAwards: Int,
    val nameEn: String,
    val nameRu: String,
    val personId: Int,
    val posterUrl: String,
    val profession: String,
    val sex: String,
    val spouses: List<Spouse>,
    val webUrl: String
)