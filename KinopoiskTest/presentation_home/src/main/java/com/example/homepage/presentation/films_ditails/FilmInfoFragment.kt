package com.example.homepage.presentation.films_ditails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.core.tools.BaseFragment
import com.example.homepage.databinding.FragmentFilmInfoBinding
import com.example.homepage.presentation.films_ditails.adapters_delegates.adapters.InfoAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class FilmInfoFragment : BaseFragment<FragmentFilmInfoBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentFilmInfoBinding.inflate(inflater)

    private val viewModel by viewModel<FilmInfoViewModel>()

   /*private val args by navArgs<>()*/

    private val adapter by lazy { InfoAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getFilmForID(TEST_ID)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.film.collect {
                binding.detailRecyclerView.adapter = adapter
                    adapter.items = it
            }
        }

    }


    companion object {
        const val TEST_ID = 301
    }
}