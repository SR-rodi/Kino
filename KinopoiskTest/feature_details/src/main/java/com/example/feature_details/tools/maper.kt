package com.example.feature_details.tools

import android.util.Log
import com.example.core.tools.CategoryFromAdapter
import com.example.core.tools.general.InfoFilms
import com.example.feature_database.entity.FilmEntity
import com.example.feature_database.repository.CollectionsFilmsRepository
import com.example.feature_details.data.ImageCategory
import com.example.feature_details.data.detailsFilm_page.dto.GalleryDTO
import com.example.feature_details.data.detailsFilm_page.dto.StaffFromFilmsDTO
import com.example.feature_details.data.detailsFilm_page.dto.InfoFilmDTO
import com.example.feature_details.data.detailsFilm_page.model.*
import com.example.feature_details.data.detailsFilm_page.response.ResponseFilmGallery
import com.example.feature_details.data.details_staff.BestFilm

fun InfoFilmDTO.toPosterFilms() =
    PosterFilm(
        kinopoiskId, countries, genres, nameRu, nameOriginal, posterUrl,
        ratingAgeLimits, year, filmLength, ratingKinopoisk, shortDescription, description,
        isFavorite,isLike,isLook
    )

fun InfoFilmDTO.toDescriptions() =
    DescriptionFilms(shortDescription, description)

fun InfoFilmDTO.toBestFilms() =
    BestFilm(nameRu, posterUrl, genres, ratingKinopoisk.toString(), kinopoiskId)

fun InfoFilmDTO.toFilmEntity() =
    FilmEntity(nameRu, posterUrl, genres, ratingKinopoisk.toString(), kinopoiskId)

fun InfoFilmDTO?.getStatusFromDataBase(
    fimEntity: FilmEntity,
) {
    this?.isLike = fimEntity.isLike
    this?.isFavorite = fimEntity.isFavorite
    this?.isLook = fimEntity.isLook

}


fun StaffFromFilmsDTO.toStaff() =
    StaffFromFilms(description, nameRu, posterUrl, professionText, staffId)

fun List<StaffFromFilmsDTO>.createStaffList(): List<InfoFilms> {
    val staffList = mutableListOf<StaffFromFilms>()
    val actorList = mutableListOf<StaffFromFilms>()
    forEach { dto ->
        if (dto.professionKey == "ACTOR") actorList.add(dto.toStaff())
        else staffList.add(dto.toStaff())
    }
    return listOf(
        CategoryFromAdapter("В фильме снимались", actorList, true),
        CategoryFromAdapter("Над фильмом работали", staffList, false)
    )
}

fun ResponseFilmGallery.toImageCategory(imageCategory: ImageCategory): ImageCategory {
    imageCategory.pages = totalPages
    imageCategory.total = total
    imageCategory.itemsList = items as MutableList<GalleryDTO>
    if (imageCategory.pages > 1) imageCategory.itemsList.add(GalleryDTO("Next", ""))
    return imageCategory
}