package com.example.feature_details.domein

import com.example.core.tools.API_KEY
import com.example.feature_details.data.details_staff.StaffDetailsDTO
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