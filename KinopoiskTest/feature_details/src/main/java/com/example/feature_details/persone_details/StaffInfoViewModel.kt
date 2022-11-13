package com.example.feature_details.persone_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_details.details_staff.Film
import com.example.feature_details.details_staff.ProfessionKey
import com.example.feature_details.details_staff.StaffDetailsDTO
import com.example.feature_details.repository_ipl.NetworkStaffRepositoryImpl
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class StaffInfoViewModel(
    private val networkRepository: NetworkStaffRepositoryImpl
) : ViewModel() {

    private val _staff =
        MutableSharedFlow<StaffDetailsDTO>()
    val staff = _staff.asSharedFlow()

    private val _films =
        MutableSharedFlow<List<ListFilmsInCategory>>()
    val films = _films.asSharedFlow()


    private val listActor = mutableListOf<Film>()
    private val listDirector = mutableListOf<Film>()
    private val listWriter = mutableListOf<Film>()
    private val listProducer = mutableListOf<Film>()
    private val listHimSelf = mutableListOf<Film>()

    fun getStaff(staffID: Int) {
        viewModelScope.launch {
            val person = networkRepository.getGalleryFilmsByID(staffID)
            createListProfession(person.films)
            _staff.emit(networkRepository.getGalleryFilmsByID(staffID))
            _films.emit(
                listOf(
                    ListFilmsInCategory(ProfessionKey.ACTOR, listActor),
                    ListFilmsInCategory(ProfessionKey.DIRECTOR, listDirector),
                    ListFilmsInCategory(ProfessionKey.WRITER, listWriter),
                    ListFilmsInCategory(ProfessionKey.PRODUCER, listProducer),
                    ListFilmsInCategory(ProfessionKey.HIMSELF, listHimSelf)
                )
            )
        }
    }

    private fun createListProfession(list: List<Film>) {

        list.forEach { film ->
            when (film.professionKey) {
                ProfessionKey.ACTOR.name -> listActor.add(film)
                ProfessionKey.DIRECTOR.name -> listDirector.add(film)
                ProfessionKey.WRITER.name -> listWriter.add(film)
                ProfessionKey.PRODUCER.name -> listProducer.add(film)
                else -> listHimSelf.add(film)
            }
        }
    }
}

class ListFilmsInCategory(val professionKey: ProfessionKey,val films:List<Film>)