package com.example.feature_details.data.detailsFilm_page.dto

import com.example.core.tools.base_model.BaseGallery
import com.example.core.tools.base_model.FullGalleryImage

class GalleryDTO(
    override val imageUrl: String,
    override val previewUrl: String
):BaseGallery{
}