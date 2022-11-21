package com.example.feature_details.presentation.films_ditails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.tools.all.NestedInoFilms
import com.example.feature_details.data.ImageCategory
import com.example.feature_details.data.detailsFilm_page.dto.GalleryDTO
import com.example.feature_details.data.detailsFilm_page.model.*
import com.example.feature_details.tools.createStaffList
import com.example.feature_details.domein.repository_ipl.NetworkFilmDetailsRepositoryImpl
import com.example.feature_details.tools.toDescriptions
import com.example.feature_details.tools.toPosterFilms
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class FilmInfoViewModel(
    private val networkRepository: NetworkFilmDetailsRepositoryImpl,
    private val savedStateHandle: SavedStateHandle
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

            val galleryList = mutableListOf<GalleryDTO>()
            val imageCategory = ImageCategory.values()

            for (position in 0..imageCategory.lastIndex) {
                if (galleryList.size < GALLERY_SIZE) galleryList
                    .addAll(networkRepository.getGalleryFilmsByID(id, imageCategory[position].name).items)
                else break
            }

            listInfo.add(CategoryGallery(galleryList))
            listInfo.add(CategorySimilar(networkRepository.getSimilarFilmsByID(id)))
            _film.emit(Pair(infoFilms.toPosterFilms(), listInfo))
        }
    }

    companion object {
        private const val GALLERY_SIZE = 20
    }

}