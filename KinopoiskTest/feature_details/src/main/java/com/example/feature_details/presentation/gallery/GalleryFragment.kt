package com.example.feature_details.presentation.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.core.tools.BaseFragment
import com.example.feature_details.data.ImageCategory
import com.example.feature_details.databinding.FragmentRecyclerBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class GalleryFragment() : BaseFragment<FragmentRecyclerBinding>() {
    override fun initBinding(inflater: LayoutInflater) =
        FragmentRecyclerBinding.inflate(inflater)

    private val viewModel by viewModel<GalleryViewModel>()

    private val adapter = GalleryAdapter { clickOnNextButton() }

    private var lastPosition = 0


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()

        binding.filmsRecyclerView.layoutManager = setGridLayoutManager()

        adapter.items = viewModel.startList()
        lastPosition = viewModel.startList().lastIndex

        binding.filmsRecyclerView.adapter = adapter

    }

    private fun setGridLayoutManager() = GridLayoutManager(requireContext(), 2).apply {
        spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int) = if (position % 3 == 0) 2 else 1
        }
    }


    private fun clickOnNextButton() = viewModel.getGallery(301)

    private fun observe() =
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.galleryList.collect {
                adapter.items = it
                binding.filmsRecyclerView
                    .adapter?.notifyItemRangeChanged(lastPosition, it.lastIndex)
                lastPosition = it.lastIndex
            }
        }


}