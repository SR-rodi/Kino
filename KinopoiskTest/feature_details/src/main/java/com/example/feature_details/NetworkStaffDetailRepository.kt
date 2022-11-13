package com.example.feature_details

import com.example.feature_details.details_staff.StaffDetailsDTO

interface NetworkStaffDetailRepository {

  suspend fun  getGalleryFilmsByID(id:Int): StaffDetailsDTO
}