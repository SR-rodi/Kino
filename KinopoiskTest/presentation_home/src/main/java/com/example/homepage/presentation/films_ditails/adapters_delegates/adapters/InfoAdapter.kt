package com.example.homepage.presentation.films_ditails.adapters_delegates.adapters

import com.example.data.detailsFilm_page.model.InfoFilms
import com.example.homepage.presentation.films_ditails.CategoryInfo
import com.example.homepage.presentation.films_ditails.adapters_delegates.delegates.*
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter


class InfoAdapter(
    private val onClickItem:(category: CategoryInfo)->Unit
) : ListDelegationAdapter<List<InfoFilms>>() {
    init {
        delegatesManager.addDelegate(descriptionAdapter())
            .addDelegate(categoryStaffAdapter{onClickItem(it)})
            .addDelegate(categoryGalleryAdapter{onClickItem(it)})
            .addDelegate(categorySimilarAdapter{onClickItem(it)})

        setItems(items)
    }
}