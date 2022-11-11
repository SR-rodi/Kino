package com.example.data.home_page.films.general

import com.google.gson.annotations.SerializedName

class Genre(
    @SerializedName("genre") override val info: String,
    override val id: Int
): GenreCountry