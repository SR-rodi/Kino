package com.example.homepage.presentation.films_ditails.adapters_delegates.adapters


import com.example.data.detailsFilm_page.model.NestedInoFilms
import com.example.homepage.presentation.films_ditails.CategoryInfo
import com.example.homepage.presentation.films_ditails.adapters_delegates.delegates.galleryAdapter
import com.example.homepage.presentation.films_ditails.adapters_delegates.delegates.similarAdapter
import com.example.homepage.presentation.films_ditails.adapters_delegates.delegates.staffAdapter
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter


class NestedAdapterInInfo(
    onClickItem: (category: CategoryInfo) -> Unit
) : ListDelegationAdapter<List<NestedInoFilms>>() {
    init {
        delegatesManager.addDelegate(staffAdapter { onClickItem(it) })
            .addDelegate(galleryAdapter { onClickItem(it) })
            .addDelegate(similarAdapter { onClickItem(it) })

        setItems(items)
    }
}