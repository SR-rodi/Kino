package com.example.feature_details.persone_details

import com.example.feature_details.databinding.ItemFilmographyBinding
import com.example.feature_details.details_staff.Film
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding


fun descriptionAdapter() =
    adapterDelegateViewBinding<Film, Film, ItemFilmographyBinding>({ layoutInflater, root ->
        ItemFilmographyBinding.inflate(layoutInflater, root, false)
    }) {

        bind {
            binding.name.text= item.nameRu
            binding.description.text = item.description

        }
    }