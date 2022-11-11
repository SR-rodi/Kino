package com.example.data.detailsFilm_page.response

import com.example.data.detailsFilm_page.dto.SimilarFilmsDTO

data class ResponseSimilar(
    val items: List<SimilarFilmsDTO>,
)