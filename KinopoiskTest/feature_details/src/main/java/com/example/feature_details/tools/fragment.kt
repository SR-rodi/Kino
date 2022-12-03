package com.example.feature_details.tools

import android.app.AlertDialog
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.core.R
import com.example.core.tools.adapter.home.NestedAdapterBase
import com.example.core.tools.base_model.category.BaseCategory
import com.example.core.tools.base_model.category.PageCategory
import com.example.core.tools.base_model.category.StartCategory
import com.example.core.tools.category.CategoryDetailsFilms
import com.example.core.tools.category.CategoryInfo
import com.example.feature_details.data.details_staff.BestFilm
import com.example.feature_details.data.details_staff.Film
import com.example.feature_details.domein.repository_ipl.FilmUseCase
import com.example.feature_details.presentation.gallery.GalleryAdapter
import com.example.feature_details.presentation.staff_details.viewModel.StaffInfoViewModel

fun Fragment.createGalleryDialog(categoryInfo: StartCategory){
    val dialog = LayoutInflater.from(requireContext())
        .inflate(com.example.feature_details.R.layout.fragment_recycler, null)
    val recyclerView = dialog.findViewById<RecyclerView>(R.id.films_recyclerView)
    val galleryAdapter = NestedAdapterBase({},{},PageCategory(CategoryInfo.GALLERY, emptyList()))

    recyclerView.layoutManager = LinearLayoutManager(requireContext(),
        LinearLayoutManager.HORIZONTAL,false)
    PagerSnapHelper().attachToRecyclerView(recyclerView)
    galleryAdapter.items = categoryInfo.list
    recyclerView.adapter = galleryAdapter
    AlertDialog.Builder(requireContext())
        .setView(dialog)
        .setOnDismissListener { }
        .create()
        .show()

    recyclerView.scrollToPosition( categoryInfo.query?.id?:0)
}

suspend fun List<Film>.getBeastFilms(filmUseCase: FilmUseCase): MutableList<BestFilm> {
    val bestListFilms = if (this.size > StaffInfoViewModel.SIZE_BEST_LIST)
        this.sortedByDescending { it.rating }.slice(0 until StaffInfoViewModel.SIZE_BEST_LIST)
    else this
    val bestFilmsInfo = mutableListOf<BestFilm>()
    if (bestListFilms.isNotEmpty()) bestListFilms.forEach {
        bestFilmsInfo.add(filmUseCase.getFilmByID(it.filmId).toBestFilms())
    }
    return bestFilmsInfo
}