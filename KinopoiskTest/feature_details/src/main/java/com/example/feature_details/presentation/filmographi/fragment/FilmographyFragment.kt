package com.example.feature_details.presentation.filmographi.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.core.tools.BaseFragment
import com.example.core.tools.extensions.withArguments
import com.example.feature_details.databinding.FragmentRecyclerBinding
import com.example.feature_details.presentation.filmographi.FilmographyCategory
import com.example.feature_details.presentation.filmographi.viewModel.FilmographyViewModel
import com.example.feature_details.presentation.filmographi.adapter.FilmographyAdapter
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.SharedFlow
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmographyFragment : BaseFragment<FragmentRecyclerBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentRecyclerBinding.inflate(inflater)

    private val adapter = FilmographyAdapter { position -> onClick(position)}

    private val viewModel by viewModel<FilmographyViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe()

        binding.filmsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        adapter.items = viewModel.startList()
        binding.filmsRecyclerView.adapter = adapter
    }


    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.poster.collect {
                adapter.items = it.first
                binding.filmsRecyclerView.adapter?.notifyItemChanged(it.second)
            }
        }
    }


    private fun onClick(position: Int) {
        viewModel.onClick(position)
    }

/*    companion object {
        fun getInstance(filmographyCategory: FilmographyCategory) =
            FilmographyFragment().withArguments {
                putParcelable("KET", filmographyCategory)
            }
    }*/

}
