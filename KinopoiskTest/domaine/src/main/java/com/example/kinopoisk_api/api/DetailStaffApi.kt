package com.example.kinopoisk_api.api

import com.example.data.details_staff.StaffDetailsDTO
import com.example.kinopoisk_api.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface DetailStaffApi {

    @Headers("X-API-KEY:$API_KEY")
    @GET("api/v1/staff/{id}")
    suspend fun getGalleryFilmsByID(
        @Path("id") id: Int,
    ): StaffDetailsDTO
}