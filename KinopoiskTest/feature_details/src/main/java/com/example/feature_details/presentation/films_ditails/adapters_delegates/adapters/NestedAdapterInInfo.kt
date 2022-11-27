package com.example.feature_details.presentation.films_ditails.adapters_delegates.adapters


import android.util.Log
import com.example.core.tools.all.CategoryInfo
import com.example.core.tools.all.NestedInoFilms
import com.example.feature_database.entity.FilmsCollectionEntity
import com.example.feature_details.presentation.films_ditails.adapters_delegates.delegates.bottomSheetDelegate
import com.example.feature_details.presentation.films_ditails.adapters_delegates.delegates.galleryCategoryDelegate
import com.example.feature_details.presentation.films_ditails.adapters_delegates.delegates.similarAdapter
import com.example.feature_details.presentation.films_ditails.adapters_delegates.delegates.staffAdapter
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

class CollectionAdapter(onClickItem: (collection:FilmsCollectionEntity) -> Unit) : ListDelegationAdapter<List<FilmsCollectionEntity>>() {

    init {
        Log.d("Kart","${items?.size}")
        delegatesManager.addDelegate(bottomSheetDelegate{onClickItem(it)})
        setItems(items)
    }
}
