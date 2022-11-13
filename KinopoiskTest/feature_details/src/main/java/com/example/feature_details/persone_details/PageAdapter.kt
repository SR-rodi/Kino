package com.example.feature_details.persone_details

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.feature_details.details_staff.ProfessionKey

class PageAdapter(
    private val fragment: Fragment,
    private val listCategoryFilms:List<ListFilmsInCategory>
) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = ProfessionKey.values().size


    override fun createFragment(position: Int): Fragment {

     return   FilmographyFragment(listCategoryFilms[position])
    }
}

