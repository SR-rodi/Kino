package com.example.homepage.presentation.films_ditails.adapters_delegates.adapters

import com.example.data.detailsFilm_page.model.NestedInoFilms
import com.example.homepage.presentation.films_ditails.adapters_delegates.galleryAdapter
import com.example.homepage.presentation.films_ditails.adapters_delegates.similarAdapter
import com.example.homepage.presentation.films_ditails.adapters_delegates.staffAdapter
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter


class NestedAdapterInInfo : ListDelegationAdapter<List<NestedInoFilms>>() {
    init {
        delegatesManager.addDelegate(staffAdapter())
            .addDelegate(galleryAdapter())
            .addDelegate(similarAdapter())

        setItems(items)
    }
}