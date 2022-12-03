package com.example.core.tools.adapter.home

import com.example.core.tools.adapter.delegate.*
import com.example.core.tools.adapter.diffutil.NestedItemDiffUtil
import com.example.core.tools.all.NestedInfoInCategory
import com.example.core.tools.base_model.category.BaseDetailsCategory
import com.example.core.tools.base_model.category.PageCategory
import com.example.core.tools.category.CategoryInfo
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class NestedAdapterBase(
    onClickItem:(pageCategory: PageCategory)->Unit,
    onClickAll:(startCategory: BaseDetailsCategory)->Unit,
    category: BaseDetailsCategory
): AsyncListDifferDelegationAdapter<NestedInfoInCategory>(NestedItemDiffUtil()) {
    init {
        delegatesManager
            .setFallbackDelegate(delegateStun())
            .addDelegate(delegateBaseFilms { onClickItem(it) })
            .addDelegate(delegateStun())
            .addDelegate(delegateNext({ onClickAll(it) }, category))
            .addDelegate(delegateStaff{onClickItem(it)})
            .addDelegate(delegateGallery{onClickItem(PageCategory(CategoryInfo.GALLERY,items))})
    }

}

