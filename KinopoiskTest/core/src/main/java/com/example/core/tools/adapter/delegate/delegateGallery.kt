package com.example.core.tools.adapter.delegate

import com.example.core.databinding.ItemDescriptionBinding
import com.example.core.databinding.ItemGalleryBinding
import com.example.core.databinding.ItemStaffBinding
import com.example.core.tools.NestedFilm
import com.example.core.tools.all.NestedInfoInCategory
import com.example.core.tools.base_model.BaseGallery
import com.example.core.tools.base_model.Staff
import com.example.core.tools.base_model.category.PageCategory
import com.example.core.tools.category.CategoryInfo
import com.example.core.tools.extensions.glide
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun delegateGallery( onClickItem:(pageCategory:PageCategory)->Unit ) = adapterDelegateViewBinding<BaseGallery, NestedInfoInCategory, ItemGalleryBinding>({ layoutInflater, root ->
    ItemGalleryBinding.inflate(layoutInflater, root, false)
}) {

    binding.poster.setOnClickListener {
        onClickItem(PageCategory(CategoryInfo.GALLERY, emptyList()))
    }

    bind{
        binding.poster.glide(item.previewUrl)
    }
}