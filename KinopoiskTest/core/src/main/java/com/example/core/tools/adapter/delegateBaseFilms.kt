package com.example.core.tools.adapter

import androidx.core.view.isInvisible
import com.example.core.databinding.ItemFilmsBinding
import com.example.core.databinding.ItemNextButtonBinding
import com.example.core.databinding.ItemStunBinding
import com.example.core.tools.NEXT_BUTTON
import com.example.core.tools.all.NestedInfoInCategory
import com.example.core.tools.base_model.category.StartCategory
import com.example.core.tools.base_model.films.BaseFilm
import com.example.core.tools.base_model.imes.ItemNext
import com.example.core.tools.base_model.imes.ItemStun
import com.example.core.tools.extensions.createName
import com.example.core.tools.extensions.glide
import com.hannesdorfmann.adapterdelegates4.AdapterDelegate
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun delegateBaseFilms(
    onClickItem: (id: Int) -> Unit,
): AdapterDelegate<List<NestedInfoInCategory>> =
    adapterDelegateViewBinding<BaseFilm, NestedInfoInCategory, ItemFilmsBinding>({ layoutInflater, root ->
        ItemFilmsBinding.inflate(layoutInflater, root, false)
    }) {

        binding.poster.setOnClickListener {
            onClickItem(item.filmId)
        }

        bind {
            binding.apply {
                filmsName.text = item.nameRu
                genreName.text = item.genres.createName()
                poster.glide(item.posterUrlPreview)
                if (item.rating != null) {
                    rating.isInvisible = false
                    rating.text = item.rating
                } else rating.isInvisible = true
            }
        }
    }

fun delegateStun() =
    adapterDelegateViewBinding<ItemStun, NestedInfoInCategory, ItemStunBinding>({ layoutInflater, root ->
        ItemStunBinding.inflate(layoutInflater, root, false)
    }) {
    }

fun delegateNext(
    onClickAll: (category: StartCategory) -> Unit,
    category: StartCategory
) = adapterDelegateViewBinding<ItemNext, NestedInfoInCategory, ItemNextButtonBinding>({ layoutInflater, root ->
        ItemNextButtonBinding.inflate(layoutInflater, root, false)
    }) {
        binding.nextButton.setOnClickListener {
            onClickAll(category)
        }
    }
