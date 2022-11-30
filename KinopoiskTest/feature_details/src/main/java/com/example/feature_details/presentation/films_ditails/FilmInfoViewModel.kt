package com.example.feature_details.presentation.films_ditails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.core.tools.*
import com.example.core.tools.all.LoadState
import com.example.core.tools.general.InfoFilms
import com.example.feature_database.entity.FilmEntity
import com.example.feature_database.repository.CollectionsFilmsRepository
import com.example.feature_database.repository.FilmDataBaseRepository
import com.example.feature_details.data.ButtonPoster
import com.example.feature_details.data.ImageCategory
import com.example.feature_details.data.detailsFilm_page.dto.GalleryDTO
import com.example.feature_details.data.detailsFilm_page.dto.InfoFilmDTO
import com.example.feature_details.data.detailsFilm_page.model.CategoryGallery
import com.example.feature_details.data.detailsFilm_page.model.CategorySimilar
import com.example.feature_details.data.detailsFilm_page.model.PosterFilm
import com.example.feature_details.domein.repository_ipl.NetworkFilmDetailsRepositoryImpl
import com.example.feature_details.tools.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

class FilmInfoViewModel(
    private val networkRepository: NetworkFilmDetailsRepositoryImpl,
    private val filmsDatabaseRepository: FilmDataBaseRepository,
    private val collectionsFilmsRepository: CollectionsFilmsRepository,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel() {


    var id = 0

    private val _film = MutableSharedFlow<Pair<PosterFilm, List<InfoFilms>>>()
    val film = _film.asSharedFlow()

    private val _filmStatus = MutableSharedFlow<FilmEntity>()
    val filmStatus = _filmStatus.asSharedFlow()

    private var filmEntity: FilmEntity? = null
    private var isEmptyInfoFromDataBase = true

    fun createID(argsInt: Int) {
        id = argsInt
    }

    fun getFilmForID() =
        viewModelScope.launch(handler + Dispatchers.IO) {
            _loadState.value = LoadState.LOADING
          val  infoFilms = networkRepository.getFilmForID(id)
            Log.e("Kart","Start Network")
            filmsDatabaseRepository.getFilmByID(id).onEach {
                filmEntity=it
                if(it == null){
                    collectionsFilmsRepository.insetFilm(infoFilms.toFilmEntity())
                    collectionsFilmsRepository.insertFilmCollection(infoFilms.toFilmEntity(), ID_HISTORY_COLLECTION)

                } else _filmStatus.emit(it)
                _film.emit(Pair(infoFilms.toPosterFilms(), createListCategoryInfo(id, infoFilms)))
            }.launchIn(viewModelScope+Dispatchers.IO)
            _loadState.value = LoadState.SUCCESS

        }



    fun workDatabase(button: ButtonPoster) =
        viewModelScope.launch(Dispatchers.IO) {
            filmEntity?.let { film ->

                when (button) {
                    ButtonPoster.LIKE ->
                        film.isLike = addOrDeleteCollections(ID_LIKE_COLLECTION, film)

                    ButtonPoster.FAVORITE ->
                        film.isFavorite = addOrDeleteCollections(ID_FAVORITE_COLLECTION, film)

                    ButtonPoster.LOOK ->
                        film.isLook = addOrDeleteCollections(ID_LOOK_COLLECTION, film)

                }
                filmsDatabaseRepository.updateFilm(film)
            }
        }

    private fun addOrDeleteCollections(collectionId: Int, film: FilmEntity): Boolean {

        val setCollection = collectionsFilmsRepository
            .getCollectionFromFilmIdAndCollectionId(film.filmId, collectionId)

        var isCheck = false
        if (collectionId == setCollection) {

            isCheck = false
            collectionsFilmsRepository.deleteFilmInCollection(collectionId, film.filmId)
        } else {
            isCheck = true
            collectionsFilmsRepository.insertFilmCollection(film, collectionId)

        }
        return isCheck

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