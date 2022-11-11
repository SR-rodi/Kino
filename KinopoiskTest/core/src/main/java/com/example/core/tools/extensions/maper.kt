package com.example.core.tools.extensions

import com.example.data.detailsFilm_page.model.CategoryAdapter
import com.example.data.detailsFilm_page.model.StaffFromFilms
import com.example.data.detailsFilm_page.dto.StaffFromFilmsDTO
import com.example.data.detailsFilm_page.dto.InfoFilmDTO
import com.example.data.detailsFilm_page.model.DescriptionFilms
import com.example.data.detailsFilm_page.model.PosterFilm

fun InfoFilmDTO.toPosterFilms() =
    PosterFilm(
        kinopoiskId, countries, genres, nameRu, nameOriginal, posterUrl,
        ratingAgeLimits, year, filmLength, ratingKinopoisk, shortDescription, description
    )

fun InfoFilmDTO.toDescriptions() =
    DescriptionFilms(shortDescription, description)

fun StaffFromFilmsDTO.toStaff() = StaffFromFilms(description, nameRu, posterUrl, professionText, staffId)

fun List<StaffFromFilmsDTO>.createStaffList(): List<CategoryAdapter> {
    val staffList = mutableListOf<StaffFromFilms>()
    val actorList = mutableListOf<StaffFromFilms>()
    forEach { dto ->
        if (dto.professionKey == "ACTOR") actorList.add(dto.toStaff())
        else staffList.add(dto.toStaff())
    }
    return listOf(
        CategoryAdapter("В фильме снимались", actorList,true),
        CategoryAdapter("Над фильмом работали", staffList,false)
    )
}
