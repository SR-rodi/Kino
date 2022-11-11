package com.example.kinopoisk_api.repo

import com.example.data.home_page.films.dto.*

interface NetworkCategoryRepository {

    suspend fun getPremieresInNetwork(year: Int, month: String): List<PremieresDTO>

    suspend fun getPopularInNetwork(page: Int): List<PopularDTO>

    suspend fun  getBestFilmsInNetwork(page: Int): List<BestFilmsDTO>

    suspend fun  getListGenreAndCounter(): CountryAndGenreDTO

    suspend fun  getFilmsGenreAndCounter(page: Int,counterID:Int,genreId:Int ): List<RandomFilmsDTO>



}