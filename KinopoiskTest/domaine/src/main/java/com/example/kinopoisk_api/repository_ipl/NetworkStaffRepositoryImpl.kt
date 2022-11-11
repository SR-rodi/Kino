package com.example.kinopoisk_api.repository_ipl

import com.example.data.details_staff.StaffDetailsDTO
import com.example.kinopoisk_api.api.DetailFilmsApi
import com.example.kinopoisk_api.api.DetailStaffApi
import com.example.kinopoisk_api.repo.NetworkStaffDetailRepository

class NetworkStaffRepositoryImpl(
    private val filmsApi: DetailStaffApi
): NetworkStaffDetailRepository {
    override suspend fun getGalleryFilmsByID(id: Int) = filmsApi.getGalleryFilmsByID(id)
}