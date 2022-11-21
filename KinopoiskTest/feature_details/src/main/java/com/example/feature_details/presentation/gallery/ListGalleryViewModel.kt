package com.example.feature_details.presentation.gallery

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_details.data.ImageCategory
import com.example.feature_details.data.detailsFilm_page.dto.GalleryDTO
import com.example.feature_details.data.detailsFilm_page.response.ResponseFilmGallery
import com.example.feature_details.domein.repository_ipl.GalleryUseCase
import com.example.feature_details.presentation.filmographi.FilmographyCategory
import com.example.feature_details.presentation.filmographi.viewModel.FilmographyListViewModel
import com.example.feature_details.tools.toImageCategory
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ListGalleryViewModel(
    private val galleryUseCase: GalleryUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _items = MutableSharedFlow<List<ImageCategory>>()
    val items get() = _items.asSharedFlow()

    fun getGallery(id: Int) {
        viewModelScope.launch {
            val listGallery = mutableListOf<ImageCategory>()
            ImageCategory.values().forEach { imageCategory ->
              val category =
                  galleryUseCase.getGallery(id, imageCategory.name, 1).toImageCategory(imageCategory)
                if (category.itemsList.isNotEmpty()) listGallery.add(category)
            }
            savedStateHandle[Gallery_CATEGORY] = listGallery
            _items.emit(listGallery)
        }
    }

    fun onClickCategory(position: Int) {
        savedStateHandle[Gallery_CATEGORY_POSITION] = position
    }

    companion object{
       const val Gallery_CATEGORY = "Gallery_CATEGORY"
       const val Gallery_CATEGORY_POSITION = "Gallery_CATEGORY_POSITION"
    }

}