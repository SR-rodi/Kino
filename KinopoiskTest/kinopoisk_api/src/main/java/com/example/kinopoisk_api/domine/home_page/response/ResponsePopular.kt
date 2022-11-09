package com.example.kinopoisk_api.domine.home_page.response

import com.example.kinopoisk_api.domine.home_page.films.PopularDTO

data class ResponsePopular(
    val films: List<PopularDTO>,
    val pagesCount: Int
)