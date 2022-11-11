package com.example.data.detailsFilm_page.model

import com.example.data.home_page.films.general.Country
import com.example.data.home_page.films.general.Genre

class PosterFilm(
    val kinopoiskId: Int,
    val countries: List<Country>,
    val genres: List<Genre>,
    val nameRu: String,
    val nameOriginal: String,
    val posterUrl: String,
    val ratingAgeLimits: String,
    val year: Int,
    val filmLength: Int,
    val rating: Double,
    val shortDescription: String,
    val description: String,
): InfoFilms {

    fun createInfoText(genreName: String, countriesName: String): String {
      return  "$rating $nameRu\n $year, $genreName\n$countriesName, " +
              "${filmLength / 60}ч ${filmLength % 60}м ${ratingAgeLimits.substring(3)}+"

    }
}
