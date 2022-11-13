package com.example.feature_details.repository_ipl

import com.example.feature_details.DetailStaffApi
import com.example.feature_details.NetworkStaffDetailRepository

class NetworkStaffRepositoryImpl(
    private val filmsApi: com.example.feature_details.DetailStaffApi
): com.example.feature_details.NetworkStaffDetailRepository {
    override suspend fun getGalleryFilmsByID(id: Int) = filmsApi.getGalleryFilmsByID(id)
}