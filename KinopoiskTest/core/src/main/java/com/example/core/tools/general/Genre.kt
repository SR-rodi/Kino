package com.example.core.tools.general

import com.google.gson.annotations.SerializedName

class Genre(
    @SerializedName("genre") override val info: String,
    override val id: Int
): GenreCountry