package com.example.feature_details.presentation.films_ditails.adapters_delegates.delegates

import androidx.core.view.isVisible
import com.example.core.databinding.ItemFilmsBinding
import com.example.core.tools.all.CategoryInfo
import com.example.core.tools.extensions.glide
import com.example.feature_details.databinding.ItemStaffBinding
import com.example.feature_details.data.detailsFilm_page.dto.SimilarFilmsDTO
import com.example.core.tools.all.NestedInfoInCategory
import com.example.feature_details.data.detailsFilm_page.model.StaffFromFilms
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun staffAdapter(onClickItem: (category: CategoryInfo) -> Unit) =
    adapterDelegateViewBinding<StaffFromFilms, NestedInfoInCategory, ItemStaffBinding>({ layoutInflater, root ->
        ItemStaffBinding.inflate(layoutInflater, root, false)
    }) {

        binding.itemCard.setOnClickListener {
            onClickItem(
                CategoryInfo.STAFF.apply { this.itemId = item.staffId })
        }

        bind {
            binding.apply {
                poster.glide(item.posterUrl)
                binding.name.text = item.nameRu
                binding.description.text = item.professionText
            }
        }
    }



fun similarAdapter(onClickItem: (category: CategoryInfo) -> Unit) =
    adapterDelegateViewBinding<SimilarFilmsDTO, NestedInfoInCategory, ItemFilmsBinding>({ layoutInflater, root ->
        ItemFilmsBinding.inflate(layoutInflater, root, false)
    }) {
        binding.itemCard.setOnClickListener {
            onClickItem(
                CategoryInfo.STAFF.apply { this.itemId = item.filmId })
        }

        bind {
            binding.poster.glide(item.posterUrlPreview)
            binding.filmsName.text = item.nameRu
            binding.rating.isVisible = true

        }
    }




