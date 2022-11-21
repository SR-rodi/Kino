package com.example.feature_details.data

import com.example.feature_details.data.detailsFilm_page.dto.GalleryDTO

enum class ImageCategory(
    val nameCategory: String,
    var itemsList: MutableList<GalleryDTO> = mutableListOf(),
    var pages: Int = 0,
    var total: Int = 0
) {
    STILL("кадры"),
    SHOOTING("изображения со съемок"),
    POSTER("постеры"),
    FAN_ART("фан-арты"),
    PROMO("промо"),
    CONCEPT("концепт-арты"),
    WALLPAPER("обои"),
    COVER("обложки"),
    SCREENSHOT("скриншоты"),
}