package com.example.homepage.data.repository

import com.example.kinopoisk_api.FilmsApi

class NetworkRepositoryHomePage(
    private val filmsApi:FilmsApi
) {

    suspend fun getPremieresInNetwork(year:Int,month:String) = filmsApi.getPremieres(year, month).items

}