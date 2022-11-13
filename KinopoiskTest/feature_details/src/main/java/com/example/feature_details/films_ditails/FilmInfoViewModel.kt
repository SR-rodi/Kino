package com.example.feature_details.films_ditails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_details.createStaffList
import com.example.feature_details.detailsFilm_page.model.CategoryGallery
import com.example.feature_details.detailsFilm_page.model.CategorySimilar
import com.example.feature_details.detailsFilm_page.model.InfoFilms
import com.example.feature_details.detailsFilm_page.model.PosterFilm
import com.example.feature_details.repository_ipl.NetworkDetailsRepositoryImpl
import com.example.feature_details.toDescriptions
import com.example.feature_details.toPosterFilms
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class FilmInfoViewModel(
    private val networkRepository: NetworkDetailsRepositoryImpl
) : ViewModel() {

    private val _film = MutableSharedFlow<Pair<PosterFilm, List<InfoFilms>>>()
    val film = _film.asSharedFlow()

    fun getFilmForID(id: Int) {
        viewModelScope.launch {
            val listInfo = mutableListOf<InfoFilms>()
            val infoFilms = networkRepository.getFilmForID(id)


            listInfo.add(infoFilms.toDescriptions())

            networkRepository.getAllStaffFilmsByID(id).createStaffList().forEach {
                listInfo.add(it)
            }

            listInfo.add(CategoryGallery(networkRepository.getGalleryFilmsByID(id).items))
            listInfo.add(CategorySimilar(networkRepository.getSimilarFilmsByID(id)))

            _film.emit(Pair(infoFilms.toPosterFilms(), listInfo))
        }
    }

}