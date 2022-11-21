package com.example.feature_details.presentation.filmographi.viewModel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_details.data.details_staff.Film
import com.example.feature_details.domein.repository_ipl.NetworkFilmDetailsRepositoryImpl
import com.example.feature_details.presentation.filmographi.FilmographyCategory
import com.example.feature_details.presentation.filmographi.viewModel.FilmographyListViewModel.Companion.FILMOGRAPHY_CATEGORY
import com.example.feature_details.presentation.filmographi.viewModel.FilmographyListViewModel.Companion.FILMOGRAPHY_CATEGORY_POSITION
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class FilmographyViewModel(
    private val networkRepository: NetworkFilmDetailsRepositoryImpl,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _poster = MutableSharedFlow<Pair<List<Film>, Int>>()
    val poster = _poster.asSharedFlow()

    private val categoryList = savedStateHandle.get<MutableList<FilmographyCategory>>(FILMOGRAPHY_CATEGORY)
    private val position = savedStateHandle.get<Int>(FILMOGRAPHY_CATEGORY_POSITION)

    private val category = position?.let { categoryList?.get(it) }

    fun onClick(position: Int) {
        viewModelScope.launch {

            if (category != null) {
                if (category.items[position].posterURL == null)
                    category.items[position].posterURL =
                        networkRepository.getFilmForID(category.items[position].filmId).posterUrl

                category.items[position].isExpand = !category.items[position].isExpand
                _poster.emit(Pair(category.items, position))
            }
        }
    }

    fun startList() = category!!.items
}