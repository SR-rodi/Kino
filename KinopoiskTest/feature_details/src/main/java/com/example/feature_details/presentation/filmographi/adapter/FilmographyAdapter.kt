package com.example.feature_details.presentation.filmographi.adapter

import androidx.core.view.isVisible
import com.example.core.tools.extensions.glide
import com.example.feature_details.data.details_staff.Film
import com.example.feature_details.databinding.ItemFilmographyBinding
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

class FilmographyAdapter(
    onclick: (position: Int) -> Unit
) : ListDelegationAdapter<List<Film>>() {
    init {
        delegatesManager.addDelegate(filmographyDelegate { onclick(it) })
    }


    private fun filmographyDelegate(onclick: (position: Int) -> Unit) =
        adapterDelegateViewBinding<Film, Film, ItemFilmographyBinding>({ layoutInflater, root ->
            ItemFilmographyBinding.inflate(layoutInflater, root, false)
        }) {


            binding.expand.setOnClickListener { onclick(absoluteAdapterPosition) }

            bind {
                binding.name.text = if (item.nameRu != null) item.nameRu
                else item.nameEn
                binding.rating.text = item.rating
                binding.description.text = item.description
                if (item.posterURL != null) {
                    binding.poster.glide(item.posterURL!!)
                } else binding.poster.isVisible = false

                binding.poster.isVisible = item.isExpand
            }
        }


}