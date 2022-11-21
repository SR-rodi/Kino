package com.example.feature_details.presentation.gallery

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.feature_details.data.ImageCategory

class GalleryTabAdapter(
    fragment: Fragment,
    private val itemCount:Int,
    private val onCategoryClick: (position: Int) -> Unit
) : FragmentStateAdapter(fragment) {
    override fun getItemCount() = itemCount

    override fun createFragment(position: Int): Fragment {
        onCategoryClick(position)
        return GalleryFragment()
    }
}

