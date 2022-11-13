package com.example.feature_details.persone_details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.example.core.tools.BaseFragment
import com.example.feature_details.databinding.FragmentFilmographyBinding

class FilmographyFragment(
   private val listFilmsInCategory: ListFilmsInCategory
) : BaseFragment<FragmentFilmographyBinding>() {
    override fun initBinding(inflater: LayoutInflater) = FragmentFilmographyBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d("Kart","профессия ${listFilmsInCategory.professionKey.profession }")
        Log.e("Kart","$ ${listFilmsInCategory.films }")

        val adapter = AdapterFilmography()
        adapter.items = listFilmsInCategory.films
        binding.filmsRecyclerView.adapter = adapter

    }

}