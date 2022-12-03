package com.example.feature_details.domein

import com.example.feature_details.data.detailsFilm_page.dto.StaffDetailsDTO

interface NetworkStaffDetailRepository {

  suspend fun  getStaffByID(id:Int): StaffDetailsDTO
}