package com.example.feature_details.domein.repository_ipl

import com.example.feature_details.data.details_staff.StaffDetailsDTO
import com.example.feature_details.domein.DetailStaffApi
import com.example.feature_details.domein.NetworkStaffDetailRepository

class NetworkStaffRepositoryImpl(
    private val staffApi: DetailStaffApi
): NetworkStaffDetailRepository {

    override suspend fun getStaffByID(id: Int) = staffApi.getGalleryFilmsByID(id)
}