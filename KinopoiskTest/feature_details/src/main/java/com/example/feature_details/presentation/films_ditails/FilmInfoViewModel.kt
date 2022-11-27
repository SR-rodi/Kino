package com.example.feature_details.presentation.films_ditails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.core.tools.BaseViewModel
import com.example.core.tools.all.LoadState
import com.example.feature_database.repository.FilmDataBaseRepository
import com.example.feature_database.entity.FilmEntity
import com.example.feature_details.data.ButtonPoster
import com.example.feature_details.data.ImageCategory
import com.example.feature_details.data.detailsFilm_page.dto.GalleryDTO
import com.example.feature_details.data.detailsFilm_page.dto.InfoFilmDTO
import com.example.feature_details.data.detailsFilm_page.model.CategoryGallery
import com.example.feature_details.data.detailsFilm_page.model.CategorySimilar
import com.example.feature_details.data.detailsFilm_page.model.InfoFilms
import com.example.feature_details.data.detailsFilm_page.model.PosterFilm
import com.example.feature_details.domein.repository_ipl.NetworkFilmDetailsRepositoryImpl
import com.example.feature_details.tools.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class FilmInfoViewModel(
    private val networkRepository: NetworkFilmDetailsRepositoryImpl,
    private val filmsDatabaseRepository: FilmDataBaseRepository,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {

    private val _film = MutableSharedFlow<Pair<PosterFilm, List<InfoFilms>>>()
    val film = _film.asSharedFlow()

    private var filmEntity: FilmEntity? = null
    private var isEmptyInfoFromDataBase = true

    fun getFilmForID(id: Int) =
        viewModelScope.launch(handler + Dispatchers.IO) {
            _loadState.value = LoadState.LOADING
            val infoFilms = networkRepository.getFilmForID(id)
            filmEntity = filmsDatabaseRepository.getFilmByID(id)

            isEmptyInfoFromDataBase = filmEntity == null

            if (filmEntity == null) filmEntity = infoFilms.toFilmEntity()
            else infoFilms.getStatusFromDataBase(filmEntity!!)

            _film.emit(Pair(infoFilms.toPosterFilms(), createListCategoryInfo(id, infoFilms)))
            _loadState.value = LoadState.SUCCESS
        }

    fun workDatabase(isSelected: Boolean, button: ButtonPoster) =
        viewModelScope.launch(Dispatchers.IO) {
            filmEntity?.let { film ->

                if (isEmptyInfoFromDataBase) filmsDatabaseRepository.insertFilm(film)
                isEmptyInfoFromDataBase = false

                when (button) {
                    ButtonPoster.LIKE -> film.isLike = isSelected
                    ButtonPoster.FAVORITE -> film.isFavorite = isSelected
                    ButtonPoster.LOOK -> film.isLook = isSelected
                }
                Log.e(
                    "Kart", "favorite =${film.isFavorite} " +
                            "like=${film.isLike} " +
                            "look=${film.isLook}"
                )
                filmsDatabaseRepository.updateFilm(film)
            }
        }


    private suspend fun createListCategoryInfo(
        id: Int,
        infoFilms: InfoFilmDTO
    ): MutableList<InfoFilms> {
        val listInfo = mutableListOf<InfoFilms>()

        listInfo.add(infoFilms.toDescriptions())

        listInfo.addAll(networkRepository.getAllStaffFilmsByID(id).createStaffList())

        listInfo.add(CategoryGallery(getGalleryList(id, GALLERY_SIZE)))

        listInfo.add(CategorySimilar(networkRepository.getSimilarFilmsByID(id)))
        return listInfo
    }

    private suspend fun getGalleryList(id: Int, gallerySize: Int): MutableList<GalleryDTO> {
        val galleryList = mutableListOf<GalleryDTO>()

        for (position in 0..ImageCategory.values().lastIndex) {
            if (galleryList.size < gallerySize) galleryList
                .addAll(
                    networkRepository.getGalleryFilmsByID(
                        id,
                        ImageCategory.values()[position].name
                    ).items
                )
            else break
        }
        return galleryList
    }

    fun createHandle() {
        savedStateHandle[BOTTOM_SHEET_FILMS] = filmEntity
        savedStateHandle[IS_EMPTY_FILM_DATABASE] = isEmptyInfoFromDataBase
    }

    companion object {
        private const val GALLERY_SIZE = 20
        const val BOTTOM_SHEET_FILMS = "bottom_sheet_films"
        const val IS_EMPTY_FILM_DATABASE = "item_in_data_base"
    }

}