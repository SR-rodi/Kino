package com.example.core.tools.adapter

import com.example.core.tools.PagedDataDelegationAdapter
import com.example.core.tools.adapter.diffutil.BaseFilmsDiffUtil
import com.example.core.tools.adapter.oldversionsadapter.BaseFilmsDiff
import com.example.core.tools.all.NestedInfoInCategory
import com.example.core.tools.base_model.films.BaseFilm
import com.hannesdorfmann.adapterdelegates4.paging.PagedListDelegationAdapter

class OneCategoryForPaging(
    private val onClickFilm:(id:Int)->Unit
) : PagedDataDelegationAdapter<NestedInfoInCategory>(BaseFilmsDiffUtil()) {
    init {
        delegatesManager.addDelegate(delegateBaseFilms { onClickFilm(it) })
    }
}