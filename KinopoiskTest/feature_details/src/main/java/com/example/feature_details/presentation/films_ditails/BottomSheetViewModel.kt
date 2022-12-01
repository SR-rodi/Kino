package com.example.feature_details.presentation.films_ditails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.tools.adapter.FilmsCollection
import com.example.feature_database.entity.FilmEntity
import com.example.feature_database.repository.CollectionsFilmsRepository
import com.example.feature_database.toFilmsCollectionList
import com.example.feature_details.presentation.films_ditails.FilmInfoViewModel.Companion.BOTTOM_SHEET_FILMS
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class BottomSheetViewModel(
    private val database: CollectionsFilmsRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val film: FilmEntity? = savedStateHandle[BOTTOM_SHEET_FILMS]

    private val _collections =
        MutableSharedFlow<List<FilmsCollection>>()
    val collections = _collections.asSharedFlow()

    fun getFilm() = film!!

    fun getCollectionFilms() {
        viewModelScope.launch(Dispatchers.IO) {
            database.getAllCollection().collect {
                val activeCollection =
                    film?.let { film -> database.getCollectionFromFilmId(film.filmId) }
                _collections.emit(it.toFilmsCollectionList(activeCollection))
            }
        }
    }

    fun addCollection(nameCollection: String) {
        viewModelScope.launch(Dispatchers.IO) {
            database.addCollection(nameCollection)
        }
    }

    fun addFilmsInCollection(collection: FilmsCollection, isSelect: Boolean) {

        viewModelScope.launch(Dispatchers.IO) {
            if (film != null)
                if (collection.isCheck)
                    database.insertFromBottomShit(film, collection.id!!, isSelect)
                else database.deleteFilmInCollectionFromBottomShit(collection.id!!, film,isSelect)
        }
    }
}
