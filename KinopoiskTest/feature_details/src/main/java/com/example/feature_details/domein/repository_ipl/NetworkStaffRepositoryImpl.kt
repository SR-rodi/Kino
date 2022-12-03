package com.example.feature_details.domein.repository_ipl

import com.example.adapterdelegate.OverallApi
import com.example.feature_details.domein.DetailFilmsApi
import com.example.feature_details.domein.NetworkStaffDetailRepository

class NetworkStaffRepositoryImpl(
    private val staffApi: DetailFilmsApi
): NetworkStaffDetailRepository {

    override suspend fun getStaffByID(id: Int) = staffApi.getStaffByID(id)
}