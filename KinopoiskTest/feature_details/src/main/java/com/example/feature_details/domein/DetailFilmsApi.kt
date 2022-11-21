package com.example.feature_details.domein

import com.example.core.tools.API_KEY
import com.example.core.tools.BASE_FILMS
import com.example.core.tools.STAFF
import com.example.feature_details.data.detailsFilm_page.dto.InfoFilmDTO
import com.example.feature_details.data.detailsFilm_page.dto.StaffFromFilmsDTO
import com.example.feature_details.data.detailsFilm_page.response.ResponseFilmGallery
import com.example.feature_details.data.detailsFilm_page.response.ResponseSimilar
import com.example.feature_details.data.details_staff.StaffDetailsDTO
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface DetailFilmsApi {

    @Headers("X-API-KEY:$API_KEY")
    @GET("$BASE_FILMS{id}")
    suspend fun getFilmsByID(
        @Path("id") id: Int
    ): InfoFilmDTO

    @Headers("X-API-KEY:$API_KEY")
    @GET(STAFF)
    suspend fun getStaffFilmsByID(@Query("filmId") id: Int): List<StaffFromFilmsDTO>

    @Headers("X-API-KEY:$API_KEY")
    @GET("api/v2.2/films/{id}/images")
    suspend fun getGalleryFilmsByID(
        @Path("id") id: Int,
        @Query("type") type: String = "SHOOTING",
        @Query("page") page: Int = 1,
    ): ResponseFilmGallery

    @Headers("X-API-KEY:$API_KEY")
    @GET("api/v2.2/films/{id}/similars")
    suspend fun getSimilarFilmsByID(
        @Path("id") id: Int,
    ): ResponseSimilar

}