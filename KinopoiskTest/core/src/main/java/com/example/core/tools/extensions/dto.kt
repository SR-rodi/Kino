package com.example.core.tools.extensions

import com.example.data.home_page.films.dto.CountryAndGenreDTO
import kotlin.random.Random
import kotlin.random.nextInt

fun CountryAndGenreDTO.getRandomCounter()=
    countries[Random.nextInt(0..countries.lastIndex)]

fun CountryAndGenreDTO.getRandomGenre()
        = genres[Random.nextInt(0..genres.lastIndex)]
