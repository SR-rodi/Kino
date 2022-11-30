package com.example.presentation_profile

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.tools.CategoryFromAdapter
import com.example.core.tools.ID_HISTORY_COLLECTION
import com.example.core.tools.ID_LOOK_COLLECTION
import com.example.core.tools.all.NestedInfoInCategory
import com.example.core.tools.general.InfoFilms
import com.example.feature_database.entity.FilmEntity
import com.example.feature_database.repository.CollectionsFilmsRepository
import com.example.feature_database.toNestedCollection
import com.example.feature_database.toNestedFilms
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val database: CollectionsFilmsRepository
) : ViewModel() {

    private var listLikeFilms = listOf<FilmEntity?>()
    private var listFavorite = listOf<FilmEntity?>()
    private val profileList = MutableSharedFlow<List<InfoFilms>>()
    val profile = profileList.asSharedFlow()


    fun getCollection() {
        viewModelScope.launch(Dispatchers.IO) {
            getLikeCollections()
            database.getAllCollection().onEach {
                Log.d("Kart", "любимые фильмы $listFavorite")
                profileList.emit(
                    listOf(
                        CategoryFromAdapter("Просмотренно", listLikeFilms.toNestedFilms()),
                        CategoryCollection("Коллекции", it.toNestedCollection()),
                        CategoryFromAdapter("Вас интеремовало", listFavorite.toNestedFilms())
                    )
                )
            }.launchIn(viewModelScope)
        }
    }

    class CategoryCollection(
        val category: String,
        val list: List<NestedInfoInCategory>,
    ) : InfoFilms {

    }

    private fun getLikeCollections() {
        var list = database.getFilmsFromCollectionID(ID_LOOK_COLLECTION)
        listLikeFilms = database.getFilmsFromListID(list)

        list = database.getFilmsFromCollectionID(ID_HISTORY_COLLECTION)
        listFavorite = database.getFilmsFromListID(list)

    }
}