package com.example.core.tools.adapter

import com.example.core.tools.adapter.diffutil.BaseFilmsDiffUtil
import com.example.core.tools.all.NestedInfoInCategory
import com.example.core.tools.base_model.category.StartCategory
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter

class NestedAdapterBase(
    onClickItem:(id:Int)->Unit,
    onClickAll:(startCategory: StartCategory )->Unit,
    category: StartCategory
): AsyncListDifferDelegationAdapter<NestedInfoInCategory>(BaseFilmsDiffUtil()) {
    init {
        delegatesManager.addDelegate(delegateBaseFilms{onClickItem(it)})
            .addDelegate(delegateStun())
            .addDelegate(delegateNext({ onClickAll(it) },category))
    }

}