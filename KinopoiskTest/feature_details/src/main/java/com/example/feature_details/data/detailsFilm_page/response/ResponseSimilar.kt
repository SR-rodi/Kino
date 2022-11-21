package com.example.feature_details.data.detailsFilm_page.response

import com.example.feature_details.data.detailsFilm_page.dto.SimilarFilmsDTO

data class ResponseSimilar(
    val items: List<SimilarFilmsDTO>,
)