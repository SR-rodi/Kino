package com.example.feature_details.presentation.films_ditails

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_database.entity.FilmEntity
import com.example.feature_database.entity.FilmsCollectionEntity
import com.example.feature_database.repository.CollectionsFilmsRepository
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
        MutableSharedFlow<List<FilmsCollectionEntity>>()
    val collections = _collections.asSharedFlow()

    private var isFilmInDB = false

    private var listCollection = listOf<FilmsCollectionEntity>()


    fun getFilm() = film!!

    fun getCollectionFilms() {
        viewModelScope.launch(Dispatchers.IO) {
            database.getAllCollection().collect {
                listCollection = listOf()
                _collections.emit(it)
            }
            film?.let { film -> isFilmInDB = checkFilmsInDatabase(film.filmId) }
        }
    }

    private fun checkFilmsInDatabase(id: Int) =
        database.getFilmFromID(id) != null

    fun addCollection(nameCollection: String) {
        viewModelScope.launch(Dispatchers.IO) {
            database.addCollection(nameCollection)
        }
    }

    fun addFilmsInCollection() {
        viewModelScope.launch(Dispatchers.IO) {
            if (collectionList != null && film != null) {
                database.insertFilm(film,collectionList!!)
            }
        }

    }
fun getFilmsCollection(){
    viewModelScope.launch(Dispatchers.IO) {

            Log.e("Kart",database.addFilmToCollection(301,collectionList!!.id!!).toString())

    }
}

    private var collectionList: FilmsCollectionEntity? = null
    fun addUpdateList(collection: FilmsCollectionEntity) {
        collectionList = collection
    }

}
