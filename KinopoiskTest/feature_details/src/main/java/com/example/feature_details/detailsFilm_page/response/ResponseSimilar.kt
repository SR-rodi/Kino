package com.example.feature_details.detailsFilm_page.response

import com.example.feature_details.detailsFilm_page.dto.SimilarFilmsDTO

data class ResponseSimilar(
    val items: List<SimilarFilmsDTO>,
)