package com.example.homepage.presentation.films_ditails.adapters_delegates.adapters

import com.example.data.detailsFilm_page.model.InfoFilms
import com.example.homepage.presentation.films_ditails.adapters_delegates.*
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter


class InfoAdapter : ListDelegationAdapter<List<InfoFilms>>() {
    init {
        delegatesManager.addDelegate(descriptionAdapter())
            .addDelegate(posterAdapter())
            .addDelegate(categoryStaffAdapter())
            .addDelegate(categoryGalleryAdapter())
            .addDelegate(categorySimilarAdapter())

        setItems(items)
    }
}