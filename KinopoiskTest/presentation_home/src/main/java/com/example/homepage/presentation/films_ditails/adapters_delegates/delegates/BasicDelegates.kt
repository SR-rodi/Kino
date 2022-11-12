package com.example.homepage.presentation.films_ditails.adapters_delegates.delegates

import androidx.recyclerview.widget.GridLayoutManager
import com.example.data.detailsFilm_page.CategoryInfo
import com.example.data.detailsFilm_page.model.CategoryAdapter
import com.example.data.detailsFilm_page.model.*
import com.example.homepage.databinding.ItemCategoryBinding
import com.example.homepage.databinding.ItemDescriptionBinding
import com.example.homepage.presentation.films_ditails.adapters_delegates.adapters.NestedAdapterInInfo
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

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

fun categoryGalleryAdapter(onClickItem: (category: CategoryInfo) -> Unit) =
    adapterDelegateViewBinding<CategoryGallery, InfoFilms, ItemCategoryBinding>({ layoutInflater, root ->
        ItemCategoryBinding.inflate(layoutInflater, root, false)
    }) {


        bind {
            binding.categoryName.text = item.category
            val adapter = NestedAdapterInInfo{ onClickItem(it) }
            binding.filmsRecyclerView.adapter = adapter
            adapter.items = item.listValue
        }
    }

fun categorySimilarAdapter(onClickItem: (category: CategoryInfo) -> Unit) =
    adapterDelegateViewBinding<CategorySimilar, InfoFilms, ItemCategoryBinding>({ layoutInflater, root ->
        ItemCategoryBinding.inflate(layoutInflater, root, false)
    }) {


        bind {
            binding.categoryName.text = item.category
            val adapter = NestedAdapterInInfo { onClickItem(it) }
            binding.filmsRecyclerView.adapter = adapter
            adapter.items = item.listValue
        }
    }

fun categoryStaffAdapter(onClickItem: (category: CategoryInfo) -> Unit) =
    adapterDelegateViewBinding<CategoryAdapter, InfoFilms, ItemCategoryBinding>({ layoutInflater, root ->
        ItemCategoryBinding.inflate(layoutInflater, root, false)
    }) {


        bind {
            binding.apply {
                categoryName.text = item.category
                this.buttonAll.text = item.listValue.size.toString()
                var spanCount = 2

                if (item.isActor) spanCount = 4

                val adapter = NestedAdapterInInfo { onClickItem(it) }
                filmsRecyclerView.adapter = adapter
                adapter.items = item.listValue

                filmsRecyclerView.layoutManager = GridLayoutManager(
                    filmsRecyclerView.context, spanCount, GridLayoutManager.HORIZONTAL, false
                )
            }
        }
    }


