package com.example.kinopoisk_api.domine.repo

import com.example.kinopoisk_api.domine.api.FilmsApi

class NetworkRepository(
    private val filmsApi: FilmsApi
) {

    suspend fun getPremieresInNetwork(year: Int, month: String) =
        filmsApi.getPremieres(year, month).items

    suspend fun getPopularInNetwork(page: Int) = filmsApi.getPopular(page).films

    suspend fun  getBestFilmsInNetwork(page: Int) = filmsApi.getBest(page).films

    suspend fun  getListGenreAndCounter()= filmsApi.getListGenreAndCountry()

    suspend fun  getFilmsGenreAndCounter(page: Int,counterID:Int,genreId:Int )=
        filmsApi.getFilmsByGenreAndCountry(page,counterID,genreId).items



/*    suspend fun getListGenre()= filmsApi.getListGenreAndCountry().genres

    suspend fun getListCounter()= filmsApi.getListGenreAndCountry().countries*/

}