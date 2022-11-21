package com.example.feature_details.presentation.staff_details.adapter


import com.example.feature_details.data.details_staff.Film
import com.example.feature_details.presentation.staff_details.descriptionAdapter
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class AdapterFilmography: ListDelegationAdapter<List<Film>>() {
    init {
        delegatesManager.addDelegate(descriptionAdapter())
    }
}