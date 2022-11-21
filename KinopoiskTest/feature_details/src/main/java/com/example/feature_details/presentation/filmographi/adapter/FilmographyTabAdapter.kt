package com.example.feature_details.presentation.filmographi.adapter

import androidx.fragment.app.Fragment
import androidx.lifecycle.SavedStateHandle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.feature_details.presentation.filmographi.FilmographyCategory
import com.example.feature_details.presentation.filmographi.fragment.FilmographyFragment
import com.example.feature_details.presentation.filmographi.viewModel.FilmographyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmographyTabAdapter(
    fragment: Fragment,
    private val itemCount:Int,
    private val onCategoryClick: (position:Int)->Unit
) : FragmentStateAdapter(fragment) {


    override fun getItemCount() = itemCount

    override fun createFragment(position: Int): Fragment {
        onCategoryClick(position)
        return FilmographyFragment()
    }
}