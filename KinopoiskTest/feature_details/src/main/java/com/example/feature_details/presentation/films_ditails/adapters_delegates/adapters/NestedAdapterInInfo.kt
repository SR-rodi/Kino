package com.example.feature_details.presentation.films_ditails.adapters_delegates.adapters


import com.example.core.tools.all.CategoryInfo
import com.example.core.tools.all.NestedInoFilms
import com.example.feature_details.data.ImageCategory
import com.example.feature_details.presentation.films_ditails.adapters_delegates.delegates.*
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter


class NestedAdapterInInfo(
    onClickItem: (category: CategoryInfo) -> Unit
) : ListDelegationAdapter<List<NestedInoFilms>>() {
    init {
        delegatesManager.addDelegate(staffAdapter { onClickItem(it) })
            .addDelegate(galleryCategoryDelegate {
                onClickItem(it.apply { items?.let { items -> it.itemList = items } })
            })
            .addDelegate(similarAdapter { onClickItem(it) })

        setItems(items)
    }
}