package com.example.kinopoisk_api.domine.api


import com.example.kinopoisk_api.*
import com.example.kinopoisk_api.domine.home_page.CountryAndGenreDTO
import com.example.kinopoisk_api.domine.home_page.ResponseFiltresFilms
import com.example.kinopoisk_api.domine.home_page.response.ResponseFilms
import com.example.kinopoisk_api.domine.home_page.response.ResponsePopular
import com.example.kinopoisk_api.domine.home_page.response.ResponsePremieres
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface FilmsApi {
    @Headers("X-API-KEY:$API_KEY")
    @GET(PREMIERES)
    suspend fun getPremieres(
        @Query("year") year: Int, @Query("month") month: String
    ): ResponsePremieres

    @Headers("X-API-KEY:$API_KEY")
    @GET(POPULAR)
    suspend fun getPopular(
        @Query("page") page: Int,
        @Query("type") type: String = TOP_100_POPULAR
    ): ResponsePopular

    @Headers("X-API-KEY:$API_KEY")
    @GET(POPULAR)
    suspend fun getBest(
        @Query("page") page: Int,
        @Query("type") type: String = TOP_250_BEST
    ): ResponseFilms

    @Headers("X-API-KEY:$API_KEY")
    @GET(GET_FILTERS)
    suspend fun getListGenreAndCountry(): CountryAndGenreDTO

    @Headers("X-API-KEY:$API_KEY")
    @GET(FILTERS)
    suspend fun getFilmsByGenreAndCountry(
        @Query("page") page: Int = 1,
        @Query("countries") countries : Int = 3,
        @Query("genres") genres: Int = 2,
    ): ResponseFiltresFilms

}
