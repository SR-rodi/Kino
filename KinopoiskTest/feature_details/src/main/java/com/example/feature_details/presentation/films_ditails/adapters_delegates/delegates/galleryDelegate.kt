package com.example.feature_details.presentation.films_ditails.adapters_delegates.delegates

import android.view.ViewGroup
import androidx.core.view.isVisible
import com.example.core.tools.all.CategoryInfo
import com.example.core.tools.all.NestedInfoInCategory
import com.example.core.tools.extensions.glide
import com.example.feature_details.data.detailsFilm_page.dto.GalleryDTO
import com.example.feature_details.databinding.ItemGalleryBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun galleryCategoryDelegate(onClickItem: (category: CategoryInfo) -> Unit) =
    adapterDelegateViewBinding<GalleryDTO, NestedInfoInCategory, ItemGalleryBinding>({ layoutInflater, root ->
        ItemGalleryBinding.inflate(layoutInflater, root, false)
    }) {
        binding.itemCard.setOnClickListener {
            onClickItem(CategoryInfo.GALLERY.apply { this.itemId = absoluteAdapterPosition })
        }

        bind {
            binding.apply {
                poster.glide(item.previewUrl)
            }
        }
    }

fun fullGalleryDelegate(
    isDialog: Boolean,
    clickNextButton: () -> Unit
) =
    adapterDelegateViewBinding<GalleryDTO, NestedInfoInCategory, ItemGalleryBinding>({ layoutInflater, root ->
        ItemGalleryBinding.inflate(layoutInflater, root, false)
    }) {
        binding.nextButton.setOnClickListener {
            clickNextButton()
        }

        bind {
            binding.apply {
                itemCard.layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT
                if (isDialog) itemCard.layoutParams.height = 800
                if (absoluteAdapterPosition % 3 == 0) itemCard.layoutParams.height = 600
                if (item.imageUrl == "Next") {
                    poster.isVisible = false
                    binding.nextButton.isVisible = true
                } else {
                    poster.isVisible = true
                    binding.nextButton.isVisible = false
                    poster.glide(item.imageUrl)
                }
            }
        }
    }
