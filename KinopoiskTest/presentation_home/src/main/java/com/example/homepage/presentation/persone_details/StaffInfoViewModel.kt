package com.example.homepage.presentation.persone_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.details_staff.StaffDetailsDTO
import com.example.data.home_page.list_info.HomePadeList
import com.example.kinopoisk_api.repo.NetworkCategoryRepository
import com.example.kinopoisk_api.repo.NetworkStaffDetailRepository
import com.example.kinopoisk_api.repository_ipl.NetworkStaffRepositoryImpl
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StaffInfoViewModel(
    private val networkRepository: NetworkStaffRepositoryImpl
) : ViewModel() {

    private val _staff =
        MutableSharedFlow<StaffDetailsDTO>()
    val staff = _staff.asSharedFlow()

    fun getStaff(staffID: Int) {
        viewModelScope.launch {
            _staff.emit(networkRepository.getGalleryFilmsByID(staffID))
        }

    }
}