package com.example.feature_details.presentation.films_ditails.adapters_delegates.adapters

import android.util.Log
import com.example.core.tools.all.CategoryInfo
import com.example.core.tools.general.InfoFilms
import com.example.feature_details.presentation.films_ditails.adapters_delegates.delegates.categoryGalleryAdapter
import com.example.feature_details.presentation.films_ditails.adapters_delegates.delegates.categorySimilarAdapter
import com.example.feature_details.presentation.films_ditails.adapters_delegates.delegates.categoryStaffAdapter
import com.example.feature_details.presentation.films_ditails.adapters_delegates.delegates.descriptionAdapter
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class InfoAdapter(
    private val onClickItem:(category: CategoryInfo)->Unit,
    private val onClickAll:(category: CategoryInfo)->Unit,
) : ListDelegationAdapter<List<InfoFilms>>() {
    init {
        Log.e("Kart"," test $items.toString()")
        delegatesManager.addDelegate(descriptionAdapter())
            .addDelegate(categoryStaffAdapter({onClickItem(it)},{onClickAll(it)}))
            .addDelegate(categoryGalleryAdapter({onClickItem(it)},{onClickAll(it)}))
            .addDelegate(categorySimilarAdapter({onClickItem(it)},{onClickAll(it)}))

        setItems(items)
    }
}