package com.example.homepage.presentation.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.data.home_page.all.CategoryFilms
import com.example.core.tools.BaseFragment
import com.example.homepage.databinding.FragmentHomeBinding
import com.example.homepage.presentation.homepage.adapters.home_page.CategoryAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentHomeBinding.inflate(inflater)

    private val viewModel by viewModel<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createAdapter()

    }

    private fun createAdapter() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.homePageList.collect { premiers ->
                binding.categoryRecyclerView.adapter =
                    CategoryAdapter(premiers, { onClickNextButton(it) }, { onClickFilms(it) })
            }
        }
    }

    private fun onClickNextButton(categoryFilms: CategoryFilms) {


        findNavController().navigate(
            HomeFragmentDirections.actionHomeFragmentToCategoryPageFragment2(categoryFilms)
        )
    }

    private fun onClickFilms(filmID: Int) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFilmInfoFragment())
    }

}