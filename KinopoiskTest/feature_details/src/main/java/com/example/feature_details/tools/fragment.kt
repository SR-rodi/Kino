package com.example.feature_details.tools

import android.app.AlertDialog
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.core.R
import com.example.core.tools.all.CategoryInfo
import com.example.feature_details.presentation.gallery.GalleryAdapter

fun Fragment.createGalleryDialog(categoryInfo: CategoryInfo){
    val dialog = LayoutInflater.from(requireContext())
        .inflate(com.example.feature_details.R.layout.fragment_recycler, null)
    val recyclerView = dialog.findViewById<RecyclerView>(R.id.films_recyclerView)
    val galleryAdapter = GalleryAdapter(true)

    recyclerView.layoutManager = LinearLayoutManager(requireContext(),
        LinearLayoutManager.HORIZONTAL,false)
    PagerSnapHelper().attachToRecyclerView(recyclerView)
    galleryAdapter.items = categoryInfo.itemList
    recyclerView.adapter = galleryAdapter
    AlertDialog.Builder(requireContext())
        .setView(dialog)
        .setOnDismissListener { }
        .create()
        .show()

    recyclerView.scrollToPosition(categoryInfo.itemId)
}