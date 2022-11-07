package com.example.kinopoisk_api

import com.example.core.API_KEY
import com.example.core.BASE_URL
import com.example.core.PREMIERES
import com.example.core.dto.ResultDTO
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface FilmsApi {
    @Headers("X-API-KEY:$API_KEY")
    @GET(PREMIERES)
    suspend fun getPremieres (@Query("year") year: Int, @Query("month") month : String): ResultDTO
}

fun createFilmsApi(): FilmsApi {
    val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    return retrofit.create(FilmsApi::class.java)
}
