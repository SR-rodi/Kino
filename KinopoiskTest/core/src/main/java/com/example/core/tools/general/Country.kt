package com.example.core.tools.general

import com.google.gson.annotations.SerializedName

class Country(
    @SerializedName("country") override val info: String,
    override val id: Int
): GenreCountry