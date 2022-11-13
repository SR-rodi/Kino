package com.example.feature_details

import com.example.core.tools.API_KEY
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface DetailStaffApi {

    @Headers("X-API-KEY:$API_KEY")
    @GET("api/v1/staff/{id}")
    suspend fun getGalleryFilmsByID(
        @Path("id") id: Int,
    ): com.example.feature_details.details_staff.StaffDetailsDTO
}