package com.example.homepage.presentation

import android.os.Bundle
import com.example.core.R
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.kinopoisk_api.CategoryFilms
import com.example.core.tools.BaseFragment
import com.example.homepage.databinding.FragmentHomeBinding
import com.example.homepage.presentation.adapter.CategoryAdapter
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


       findNavController().navigate(HomeFragmentDirections)
        Toast.makeText(requireContext(), categoryFilms.text, Toast.LENGTH_SHORT).show()

    }

    private fun onClickFilms(name: String) {
        Toast.makeText(requireContext(), name, Toast.LENGTH_SHORT).show()
    }

}