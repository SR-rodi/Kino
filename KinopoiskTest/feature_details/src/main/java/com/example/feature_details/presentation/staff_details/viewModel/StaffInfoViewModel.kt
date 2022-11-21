package com.example.feature_details.presentation.staff_details.viewModel

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.tools.all.BaseFilms
import com.example.feature_details.data.detailsFilm_page.dto.InfoFilmDTO
import com.example.feature_details.data.detailsFilm_page.model.ListFilmsInCategory
import com.example.feature_details.data.details_staff.BestFilm
import com.example.feature_details.data.details_staff.Film
import com.example.feature_details.data.details_staff.ProfessionKey
import com.example.feature_details.data.details_staff.StaffDetailsDTO
import com.example.feature_details.domein.repository_ipl.FilmUseCase
import com.example.feature_details.domein.repository_ipl.NetworkStaffRepositoryImpl
import com.example.feature_details.tools.toBestFilms
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class StaffInfoViewModel(
    private val networkRepository: NetworkStaffRepositoryImpl,
    private val filmUseCase: FilmUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _staff =
        MutableSharedFlow<Pair<StaffDetailsDTO, List<BestFilm>>>()
    val staff = _staff.asSharedFlow()

    fun getStaff(staffID: Int) {
        viewModelScope.launch {
            val person = networkRepository.getStaffByID(staffID)
            savedStateHandle["person"] = person
            _staff.emit(Pair(person, getBeastFilms(person.films)))

        }
    }

    private suspend fun getBeastFilms(films: List<Film>): MutableList<BestFilm> {
        val bestListFilms = if (films.size > SIZE_BEST_LIST)
            films.sortedByDescending { it.rating }.slice(0 until SIZE_BEST_LIST)
        else films
        val bestFilmsInfo = mutableListOf<BestFilm>()
        if (bestListFilms.isNotEmpty()) bestListFilms.forEach {
            bestFilmsInfo.add(filmUseCase.getFilmByID(it.filmId).toBestFilms())
        }
        return bestFilmsInfo
    }

    companion object {
        const val SIZE_BEST_LIST = 5
    }

}