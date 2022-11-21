package com.example.feature_details.presentation.gallery

import com.example.core.tools.all.NestedInoFilms
import com.example.feature_details.presentation.films_ditails.adapters_delegates.delegates.fullGalleryDelegate
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class GalleryAdapter(
    private val isDialog: Boolean = false,
    private val clickNextButton: () -> Unit = {},
) : ListDelegationAdapter<List<NestedInoFilms>>() {
    init {
        delegatesManager.addDelegate(fullGalleryDelegate(isDialog){clickNextButton()})
        setItems(items)
    }
}