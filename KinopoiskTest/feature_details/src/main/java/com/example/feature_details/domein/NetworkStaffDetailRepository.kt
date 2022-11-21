package com.example.feature_details.domein

import com.example.feature_details.data.details_staff.StaffDetailsDTO

interface NetworkStaffDetailRepository {

  suspend fun  getStaffByID(id:Int): StaffDetailsDTO
}