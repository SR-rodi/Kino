package com.example.homepage.presentation.films_ditails.adapters_delegates

import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.tools.extensions.createName
import com.example.core.tools.extensions.glide
import com.example.data.detailsFilm_page.model.CategoryAdapter
import com.example.data.detailsFilm_page.model.*
import com.example.homepage.databinding.ItemCategoryBinding
import com.example.homepage.databinding.ItemDescriptionBinding
import com.example.homepage.databinding.ItemPosterInfoBinding
import com.example.homepage.presentation.films_ditails.adapters_delegates.adapters.NestedAdapterInInfo
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun posterAdapter() =
    adapterDelegateViewBinding<PosterFilm, InfoFilms, ItemPosterInfoBinding>({ layoutInflater, root ->
        ItemPosterInfoBinding.inflate(layoutInflater, root, false)
    }) {


        bind {
            binding.apply {
                poster.glide(item.posterUrl)
                this.info.text =
                    item.createInfoText(item.genres.createName(), item.countries.createName())
            }
        }
    }

fun descriptionAdapter() =
    adapterDelegateViewBinding<DescriptionFilms, InfoFilms, ItemDescriptionBinding>({ layoutInflater, root ->
        ItemDescriptionBinding.inflate(layoutInflater, root, false)
    }) {


        bind {
            binding.apply {
                binding.shortDescription.text = item.shortDescription
                binding.description.text = item.description
            }
        }
    }

fun categoryGalleryAdapter() =
    adapterDelegateViewBinding<CategoryGallery, InfoFilms, ItemCategoryBinding>({ layoutInflater, root ->
        ItemCategoryBinding.inflate(layoutInflater, root, false)
    }) {


        bind {
          binding.categoryName.text = item.category
            val adapter = NestedAdapterInInfo()
            binding.filmsRecyclerView.adapter = adapter
            adapter.items = item.listValue
        }
    }

fun categorySimilarAdapter() =
    adapterDelegateViewBinding<CategorySimilar, InfoFilms, ItemCategoryBinding>({ layoutInflater, root ->
        ItemCategoryBinding.inflate(layoutInflater, root, false)
    }) {


        bind {
            binding.categoryName.text = item.category
            val adapter = NestedAdapterInInfo()
            binding.filmsRecyclerView.adapter = adapter
            adapter.items = item.listValue
        }
    }

fun categoryStaffAdapter() =
    adapterDelegateViewBinding<CategoryAdapter, InfoFilms, ItemCategoryBinding>({ layoutInflater, root ->
        ItemCategoryBinding.inflate(layoutInflater, root, false)
    }) {


        bind {
            binding.apply {
                categoryName.text = item.category
                this.buttonAll.text = item.listValue.size.toString()
                var spanCount = 2

                if (item.isActor) spanCount = 4

                val adapter = NestedAdapterInInfo()
                filmsRecyclerView.adapter = adapter
                adapter.items = item.listValue

                filmsRecyclerView.layoutManager = GridLayoutManager(
                    filmsRecyclerView.context, spanCount, GridLayoutManager.HORIZONTAL, false
                )
            }
        }
    }


