package com.example.core.tools.adapter.collections

import com.example.core.tools.NestedCollection
import com.example.core.tools.adapter.delegate.bottomSheetDelegate
import com.example.core.tools.adapter.delegate.delegateBasCategory
import com.example.core.tools.adapter.delegate.delegateDescription
import com.example.core.tools.adapter.diffutil.CategoryDiffUtil
import com.example.core.tools.adapter.diffutil.NestedItemDiffUtil
import com.example.core.tools.adapter.oldversionsadapter.FilmsCollection
import com.example.core.tools.all.NestedInfoInCategory
import com.example.core.tools.base_model.category.BaseCategory
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
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