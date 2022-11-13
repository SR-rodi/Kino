package com.example.feature_details.films_ditails.adapters_delegates.adapters

import com.example.feature_details.detailsFilm_page.model.InfoFilms
import com.example.feature_details.films_ditails.CategoryInfo
import com.example.feature_details.films_ditails.adapters_delegates.delegates.categoryGalleryAdapter
import com.example.feature_details.films_ditails.adapters_delegates.delegates.categorySimilarAdapter
import com.example.feature_details.films_ditails.adapters_delegates.delegates.categoryStaffAdapter
import com.example.feature_details.films_ditails.adapters_delegates.delegates.descriptionAdapter
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