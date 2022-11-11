package com.example.homepage.presentation.persone_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kinopoisk_api.repo.NetworkCategoryRepository
import com.example.kinopoisk_api.repo.NetworkStaffDetailRepository
import kotlinx.coroutines.launch

class StaffInfoViewModel (
    private val networkRepository: NetworkStaffDetailRepository
        ): ViewModel() {

    fun getStaff(staffID: Int) {
        viewModelScope.launch {
            networkRepository.getGalleryFilmsByID(staffID)
        }

    }
}