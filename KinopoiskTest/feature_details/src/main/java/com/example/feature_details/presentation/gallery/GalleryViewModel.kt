package com.example.feature_details.presentation.gallery

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_details.data.ImageCategory
import com.example.feature_details.data.detailsFilm_page.dto.GalleryDTO
import com.example.feature_details.domein.repository_ipl.GalleryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class GalleryViewModel(
    private val useCase: GalleryUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    var page = 1

    private val categoryList =
        savedStateHandle.get<List<ImageCategory>>(ListGalleryViewModel.Gallery_CATEGORY)
    private val position = savedStateHandle.get<Int>(ListGalleryViewModel.Gallery_CATEGORY_POSITION)

    private val category = position?.let { categoryList?.get(it) }

    private val _galleryList = MutableStateFlow<List<GalleryDTO>>(emptyList())
    val galleryList = _galleryList.asStateFlow()

    fun getGallery(filmsId: Int) {
        page++

        viewModelScope.launch {
            if (category != null) {
                category.itemsList.removeLastOrNull()
                val response = useCase.getGallery(filmsId, category.name, page)
                category.itemsList.addAll(response.items)
                if (response.total < category.itemsList.size)
                    category.itemsList.add(GalleryDTO("Next", ""))
                if (category.itemsList.isNotEmpty())
                    _galleryList.value = category.itemsList
            }
        }

    }

    fun startList() = category!!.itemsList

}