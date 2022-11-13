package com.example.feature_details.persone_details


import com.example.feature_details.details_staff.Film
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter

class AdapterFilmography: ListDelegationAdapter<List<Film>>() {
    init {
        delegatesManager.addDelegate(descriptionAdapter())
    }
}