package com.example.core.tools.adapter.collections

import com.example.core.tools.adapter.delegate.bottomSheetDelegate
import com.example.core.tools.adapter.oldversionsadapter.FilmsCollection
import com.example.core.tools.all.NestedInfoInCategory
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class CollectionAdapter(
    onClickItem: (collection: FilmsCollection, isCheck: Boolean) -> Unit
) : ListDelegationAdapter<List<NestedInfoInCategory>>() {

    init {
        delegatesManager.addDelegate(bottomSheetDelegate { collection, isCheck ->
            onClickItem(collection, isCheck)
        })

    }


}