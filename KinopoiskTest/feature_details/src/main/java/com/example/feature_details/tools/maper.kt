package com.example.feature_details.tools

import com.example.core.tools.SIZE_LIST_VIEW
import com.example.core.tools.base_model.films.BaseFilm
import com.example.core.tools.category.CategoryInfo
import com.example.core.tools.extensions.createListForView
import com.example.feature_database.entity.FilmEntity
import com.example.feature_details.data.ImageCategory
import com.example.feature_details.data.detailsFilm_page.dto.GalleryDTO
import com.example.feature_details.data.detailsFilm_page.dto.InfoFilmDTO
import com.example.adapterdelegate.data.SimilarFilmsDTO
import com.example.feature_details.data.detailsFilm_page.model.DescriptionFilms
import com.example.feature_details.data.detailsFilm_page.model.PosterFilm
import com.example.feature_details.data.detailsFilm_page.response.ResponseFilmGallery
import com.example.feature_details.data.details_staff.BestFilm


fun InfoFilmDTO.toPosterFilms() =
    PosterFilm(
        kinopoiskId, countries, genres, nameRu, nameOriginal, posterUrl,
        ratingAgeLimits, year, filmLength, ratingKinopoisk, shortDescription, description,
        isFavorite, isLike, isLook
    )

fun InfoFilmDTO.toDescriptions() =
    DescriptionFilms(CategoryInfo.GALLERY, shortDescription, description)

fun InfoFilmDTO.toBestFilms() =
    BestFilm(nameRu, posterUrl, genres, ratingKinopoisk.toString(), kinopoiskId)

fun InfoFilmDTO.toFilmEntity() =
    FilmEntity(nameRu, posterUrl, genres, ratingKinopoisk.toString(), kinopoiskId)

fun ResponseFilmGallery.toImageCategory(imageCategory: ImageCategory): ImageCategory {
    imageCategory.pages = totalPages
    imageCategory.total = total
    imageCategory.itemsList = items as MutableList<GalleryDTO>
    if (imageCategory.pages > 1) imageCategory.itemsList.add(GalleryDTO("Next", ""))
    return imageCategory
}

fun List<SimilarFilmsDTO>.toListBaseFilm(): List<BaseFilm> {
    val list = mutableListOf<BaseFilm>()
    this.forEach {
        list.add(BaseFilm(it.nameRu, it.posterUrlPreview, filmId = it.filmId))
    }
    return list.createListForView(SIZE_LIST_VIEW) as List<BaseFilm>

}