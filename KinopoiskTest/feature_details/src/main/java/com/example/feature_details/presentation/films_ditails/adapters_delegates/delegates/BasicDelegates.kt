package com.example.feature_details.presentation.films_ditails.adapters_delegates.delegates

import android.transition.TransitionManager
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.databinding.ItemCategoryBinding
import com.example.core.tools.CategoryFromAdapter
import com.example.core.tools.category.CategoryDetailsFilms
import com.example.core.tools.general.InfoFilms
import com.example.feature_details.data.detailsFilm_page.model.*
import com.example.feature_details.databinding.ItemDescriptionBinding
import com.example.feature_details.presentation.films_ditails.adapters_delegates.adapters.NestedAdapterInInfo
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun descriptionAdapter() =
    adapterDelegateViewBinding<DescriptionFilms, InfoFilms, ItemDescriptionBinding>({ layoutInflater, root ->
        ItemDescriptionBinding.inflate(layoutInflater, root, false)
    }) {

        var isCollapsed = false

        bind {
            binding.apply {
                TransitionManager.beginDelayedTransition(binding.animLayout)
                binding.shortDescription.text = item.shortDescription
                binding.description.text = item.description
            }
            binding.description.setOnClickListener {
                if (isCollapsed) {
                    binding.description.maxLines = 3
                } else {
                    TransitionManager.beginDelayedTransition(binding.animLayout)
                    binding.description.maxLines = Int.MAX_VALUE
                }

                isCollapsed = !isCollapsed


            }
        }

    }

fun categoryGalleryAdapter(
    onClickItem: (category: CategoryDetailsFilms) -> Unit,
    onClickAll: (category: CategoryDetailsFilms) -> Unit,
) =
    adapterDelegateViewBinding<CategoryGallery, InfoFilms, ItemCategoryBinding>({ layoutInflater, root ->
        ItemCategoryBinding.inflate(layoutInflater, root, false)
    }) {

        binding.buttonAll.setOnClickListener { onClickAll(CategoryDetailsFilms.GALLERY) }

        bind {
            binding.categoryName.text = item.category
            val adapter = NestedAdapterInInfo { onClickItem(it) }
            binding.filmsRecyclerView.adapter = adapter
            adapter.items = item.listValue
        }
    }

fun categorySimilarAdapter(
    onClickItem: (category: CategoryDetailsFilms) -> Unit,
    onClickAll: (category: CategoryDetailsFilms) -> Unit,
) =
    adapterDelegateViewBinding<CategorySimilar, InfoFilms, ItemCategoryBinding>({ layoutInflater, root ->
        ItemCategoryBinding.inflate(layoutInflater, root, false)
    }) {

        binding.buttonAll.setOnClickListener { onClickAll(CategoryDetailsFilms.FILMS.apply { itemId }) }
        bind {

            binding.categoryName.text = item.category
            val adapter = NestedAdapterInInfo { onClickItem(it) }
            binding.filmsRecyclerView.adapter = adapter
            adapter.items = item.listValue
        }
    }

fun categoryStaffAdapter(
    onClickItem: (category: CategoryDetailsFilms) -> Unit,
    onClickAll: (category: CategoryDetailsFilms) -> Unit,
) =
    adapterDelegateViewBinding<CategoryFromAdapter, InfoFilms, ItemCategoryBinding>({ layoutInflater, root ->
        ItemCategoryBinding.inflate(layoutInflater, root, false)
    }) {

        binding.buttonAll.setOnClickListener { onClickAll(CategoryDetailsFilms.STAFF) }

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


