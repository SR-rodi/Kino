package com.example.feature_details.presentation.staff_details.viewModel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.tools.BaseViewModel
import com.example.core.tools.NAVIGATE__TO_FILMOGRAPHY
import com.example.core.tools.NAVIGATE__TO_STAFF
import com.example.core.tools.all.BaseFilms
import com.example.core.tools.all.LoadState
import com.example.feature_details.data.detailsFilm_page.dto.InfoFilmDTO
import com.example.feature_details.data.detailsFilm_page.model.ListFilmsInCategory
import com.example.feature_details.data.details_staff.BestFilm
import com.example.feature_details.data.details_staff.Film
import com.example.feature_details.data.details_staff.ProfessionKey
import com.example.feature_details.data.details_staff.StaffDetailsDTO
import com.example.feature_details.domein.repository_ipl.FilmUseCase
import com.example.feature_details.domein.repository_ipl.NetworkStaffRepositoryImpl
import com.example.feature_details.tools.getBeastFilms
import com.example.feature_details.tools.toBestFilms
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class StaffInfoViewModel(
    private val networkRepository: NetworkStaffRepositoryImpl,
    private val filmUseCase: FilmUseCase,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val _staff =
        MutableSharedFlow<Pair<StaffDetailsDTO, List<BestFilm>>>()
    val staff = _staff.asSharedFlow()

    private val staffID: Int? = savedStateHandle[NAVIGATE__TO_STAFF]
    private var person:StaffDetailsDTO?=null

    fun getStaff() {
        viewModelScope.launch(handler + Dispatchers.IO) {
            if (staffID != null) {
                _loadState.value = LoadState.LOADING
                 person = networkRepository.getStaffByID(staffID)
                _staff.emit(Pair(person!!, person!!.films.getBeastFilms(filmUseCase)))
                _loadState.value = LoadState.SUCCESS
            }
        }
    }

    fun navigateToFilmography() {
        savedStateHandle[NAVIGATE__TO_FILMOGRAPHY] = person
    }

    companion object {
        const val SIZE_BEST_LIST = 5
    }

}