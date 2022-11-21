package com.example.feature_details.presentation.films_ditails


import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.core.R
import com.example.core.tools.BaseFragment
import com.example.core.tools.all.CategoryInfo
import com.example.core.tools.extensions.*
import com.example.feature_details.data.detailsFilm_page.model.InfoFilms
import com.example.feature_details.data.detailsFilm_page.model.PosterFilm
import com.example.feature_details.databinding.FragmentFilmInfoBinding
import com.example.feature_details.presentation.films_ditails.adapters_delegates.adapters.InfoAdapter
import com.example.feature_details.presentation.gallery.GalleryAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmInfoFragment : BaseFragment<FragmentFilmInfoBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentFilmInfoBinding.inflate(inflater)

    private val viewModel by viewModel<FilmInfoViewModel>()

    private val adapter by lazy { InfoAdapter({ onClickItem(it) },{onClickAll(it)}) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFilmForID(301/*getArgsInt()*/)

        binding.backArrow.popBackStack()

        binding.newPoster

        var isSelect = false
        binding.newPoster.like.setOnClickListener {
            it.isSelected = !isSelect
            isSelect = !isSelect
        }

        var isSelectOne = false
        binding.newPoster.favorite.setOnClickListener {
            it.isSelected = !isSelectOne
            isSelectOne = !isSelectOne
        }

        var isSelectTwo = false
        binding.newPoster.look.setOnClickListener {
            it.isSelected = !isSelectTwo
            isSelectTwo = !isSelectTwo
        }

        var isSelectT = false
        binding.newPoster.share.setOnClickListener {
            it.isSelected = !isSelectT
            isSelectT = !isSelectT
        }



        binding.newPoster.menu.setOnClickListener {
            findNavController().navigate(R.id.action_filmInfoFragment_to_bottomSheetMenuFragment)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.film.collect {
                setPoster(it.first)
                setAdapter(it.second)
            }
        }
    }

    private fun setPoster(item: PosterFilm) {
        binding.newPoster.apply {
            poster.glide(item.posterUrl)
            this.info.text =
                item.createInfoText(item.genres.createName(), item.countries.createName())
            binding.categoryTitle.text = item.nameOriginal

            binding.newPoster.share.clickAndShowShareDialog(item.nameOriginal)

        }
    }

    private fun onClickItem(categoryInfo: CategoryInfo) {
        when (categoryInfo) {
            CategoryInfo.STAFF ->
                findNavController().navigate(
                    R.id.action_filmInfoFragment_to_staffInfoFragment,
                    categoryInfo.createBundle()
                )
            CategoryInfo.GALLERY -> createDialog(categoryInfo)
            CategoryInfo.FILMS -> {}
        }

    }

    private fun onClickAll(categoryInfo: CategoryInfo) {
        when (categoryInfo) {
            CategoryInfo.STAFF ->{}
            CategoryInfo.GALLERY ->
                findNavController().navigate(R.id.action_filmInfoFragment_to_galleryFragment)
            CategoryInfo.FILMS -> {}
        }

    }


    private fun setAdapter(listIfo: List<InfoFilms>) {
        binding.detailRecyclerView.adapter = adapter
        adapter.items = listIfo

    }

    private fun createDialog(categoryInfo: CategoryInfo) {

        val a = LayoutInflater.from(requireContext())
            .inflate(com.example.feature_details.R.layout.fragment_recycler, null)
        val recyclerView = a.findViewById<RecyclerView>(R.id.films_recyclerView)
        val galleryAdapter = GalleryAdapter(true)
        recyclerView.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
        PagerSnapHelper().attachToRecyclerView(recyclerView)
        galleryAdapter.items = categoryInfo.itemList
        recyclerView.adapter = galleryAdapter
        AlertDialog.Builder(requireContext())
            .setView(a)
            .setOnDismissListener { }
            .create()
            .show()

        recyclerView.scrollToPosition(categoryInfo.itemId)
    }
}



