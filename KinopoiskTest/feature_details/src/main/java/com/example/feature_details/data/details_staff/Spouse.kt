package com.example.feature_details.data.details_staff

data class Spouse(
    val children: Int,
    val divorced: Boolean,
    val divorcedReason: String,
    val name: String,
    val personId: Int,
    val relation: String,
    val sex: String,
    val webUrl: String
)