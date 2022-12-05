package com.example.feature_details.tools

import android.app.AlertDialog
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.core.R
import com.example.core.tools.adapter.home.NestedAdapterBase
import com.example.core.tools.all.NestedInfoInCategory
import com.example.core.tools.base_model.BaseGallery
import com.example.core.tools.base_model.FullGalleryImage
import com.example.core.tools.base_model.category.PageCategory
import com.example.feature_details.data.details_staff.BestFilm
import com.example.feature_details.data.details_staff.Film
import com.example.feature_details.domein.repository_ipl.FilmUseCase
import com.example.feature_details.presentation.staff_details.viewModel.StaffInfoViewModel

fun Fragment.createGalleryDialog(pageCategory: PageCategory){
    val dialog = LayoutInflater.from(requireContext())
        .inflate(com.example.feature_details.R.layout.fragment_recycler, null)
    val recyclerView = dialog.findViewById<RecyclerView>(R.id.films_recyclerView)
    val galleryAdapter = NestedAdapterBase({},{})

    recyclerView.setPadding(0,0,0,0)
    recyclerView.layoutManager = LinearLayoutManager(requireContext(),
        LinearLayoutManager.HORIZONTAL,false)
    PagerSnapHelper().attachToRecyclerView(recyclerView)

    galleryAdapter.items = pageCategory.list.toFullGalleryImage()
    recyclerView.adapter = galleryAdapter

    AlertDialog.Builder(requireContext()).setView(dialog).create().show()

    recyclerView.scrollToPosition( pageCategory.query?.id?:0)
}