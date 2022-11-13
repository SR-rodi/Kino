package com.example.feature_details.detailsFilm_page.response

import com.example.feature_details.detailsFilm_page.dto.GalleryDTO

data class ResponseFilmGallery(
    val items: List<GalleryDTO>,
)