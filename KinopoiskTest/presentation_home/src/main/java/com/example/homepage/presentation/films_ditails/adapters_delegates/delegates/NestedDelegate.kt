package com.example.homepage.presentation.films_ditails.adapters_delegates.delegates

import androidx.core.view.isVisible
import com.example.core.databinding.ItemFilmsBinding
import com.example.core.tools.extensions.glide

import com.example.data.detailsFilm_page.dto.GalleryDTO
import com.example.data.detailsFilm_page.dto.SimilarFilmsDTO
import com.example.data.detailsFilm_page.model.NestedInoFilms
import com.example.data.detailsFilm_page.model.StaffFromFilms
import com.example.homepage.databinding.ItemGalleryBinding
import com.example.homepage.databinding.ItemStaffBinding
import com.example.homepage.presentation.films_ditails.CategoryInfo
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding
import kotlinx.android.synthetic.main.item_staff.view.*

fun staffAdapter(onClickItem:(category: CategoryInfo) -> Unit) =
    adapterDelegateViewBinding<StaffFromFilms, NestedInoFilms, ItemStaffBinding>({ layoutInflater, root ->
        ItemStaffBinding.inflate(layoutInflater, root, false)
    }) {

        binding.itemCard.setOnClickListener { onClickItem(
            CategoryInfo.STAFF.apply{ this.itemId = item.staffId })}

        bind {
            binding.apply {
                poster.glide(item.posterUrl)
                binding.name.text = item.nameRu
                binding.description.text = item.professionText
            }
        }
    }


fun galleryAdapter(onClickItem:(category: CategoryInfo) -> Unit) =
    adapterDelegateViewBinding<GalleryDTO, NestedInoFilms, ItemGalleryBinding>({ layoutInflater, root ->
        ItemGalleryBinding.inflate(layoutInflater, root, false)
    }) {
        binding.itemCard.setOnClickListener { onClickItem(CategoryInfo.GALLERY) }

        bind {
            binding.apply {
                poster.glide(item.previewUrl)
            }
        }
    }


fun similarAdapter(onClickItem:(category: CategoryInfo) -> Unit) =
    adapterDelegateViewBinding<SimilarFilmsDTO, NestedInoFilms, ItemFilmsBinding>({ layoutInflater, root ->
        ItemFilmsBinding.inflate(layoutInflater, root, false)
    }) {
        binding.itemCard.setOnClickListener { onClickItem(
            CategoryInfo.STAFF.apply{ this.itemId = item.filmId }) }

        bind {
            binding.poster.glide(item.posterUrlPreview)
            binding.filmsName.text = item.nameRu
            binding.rating.isVisible =true

        }
    }