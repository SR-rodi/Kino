package com.example.kinopoisk_api.repo

import com.example.data.details_staff.StaffDetailsDTO

interface NetworkStaffDetailRepository {

  suspend fun  getGalleryFilmsByID(id:Int): StaffDetailsDTO
}