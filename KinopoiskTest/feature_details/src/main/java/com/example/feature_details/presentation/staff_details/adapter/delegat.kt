package com.example.feature_details.presentation.staff_details.adapter

import com.example.feature_details.data.details_staff.Film
import com.example.feature_details.databinding.ItemFilmographyBinding

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