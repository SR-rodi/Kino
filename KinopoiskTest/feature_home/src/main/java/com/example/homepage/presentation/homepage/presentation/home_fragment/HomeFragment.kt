package com.example.homepage.presentation.homepage.presentation.home_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.core.tools.all.CategoryFilms
import com.example.core.tools.BaseFragment
import com.example.core.tools.extensions.createBundle
import com.example.core.R
import com.example.core.tools.CATEGORY_BUNDLE
import com.example.homepage.databinding.FragmentHomeBinding
import com.example.homepage.presentation.homepage.presentation.adapters.home_page.CategoryAdapter
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
        findNavController().navigate(R.id.action_homeFragment_to_categoryPageFragment,categoryFilms.createBundle())
    }

    private fun onClickFilms(filmID: Int) {
        Log.d("Kart",filmID.toString())
        findNavController().navigate(R.id.action_homeFragment_to_filmInfoFragment,filmID.createBundle())
    }

}