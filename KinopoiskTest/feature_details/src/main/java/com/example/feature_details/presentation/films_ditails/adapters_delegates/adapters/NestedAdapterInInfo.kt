package com.example.feature_details.presentation.films_ditails.adapters_delegates.adapters


import android.util.Log
import com.example.core.tools.FilmsCollection
import com.example.core.tools.all.CategoryInfo
import com.example.core.tools.all.NestedInfoInCategory
import com.example.feature_details.presentation.films_ditails.adapters_delegates.delegates.bottomSheetDelegate
import com.example.feature_details.presentation.films_ditails.adapters_delegates.delegates.galleryCategoryDelegate
import com.example.feature_details.presentation.films_ditails.adapters_delegates.delegates.similarAdapter
import com.example.feature_details.presentation.films_ditails.adapters_delegates.delegates.staffAdapter
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter


class NestedAdapterInInfo(
    onClickItem: (category: CategoryInfo) -> Unit
) : ListDelegationAdapter<List<NestedInfoInCategory>>() {
    init {
        delegatesManager.addDelegate(staffAdapter { onClickItem(it) })
            .addDelegate(galleryCategoryDelegate {
                onClickItem(it.apply { items?.let { items -> it.itemList = items } })
            })
            .addDelegate(similarAdapter { onClickItem(it) })

        setItems(items)
    }
}

class CollectionAdapter(
    onClickItem: (collection: FilmsCollection, isCheck:Boolean ) -> Unit) : ListDelegationAdapter<List<FilmsCollection>>() {
    init {
        Log.d("Kart","${items?.size}")
        delegatesManager.addDelegate(bottomSheetDelegate{collection,isCheck->
            onClickItem(collection,isCheck)
        })
        setItems(items)
    }
}
