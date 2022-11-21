package com.example.homepage.presentation.homepage.presentation.category_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.core.tools.BaseFragment
import com.example.core.tools.CATEGORY_BUNDLE
import com.example.core.tools.all.CategoryFilms
import com.example.core.tools.extensions.getArgsCategoryFilms
import com.example.core.tools.extensions.popBackStack
import com.example.homepage.databinding.FragmentCategoryPageBinding
import com.example.homepage.presentation.homepage.presentation.adapters.filmsAdapter.FilmAdapterFromPage
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryPageFragment : BaseFragment<FragmentCategoryPageBinding>() {

    override fun initBinding(inflater: LayoutInflater) =
        FragmentCategoryPageBinding.inflate(inflater)

    private val viewModel by viewModel<CategoryPageViewModel>()

    private val adapter by lazy { FilmAdapterFromPage() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.categoryPageRecyclerView.adapter = adapter

        val category = getArgsCategoryFilms()

        binding.categoryTitle.text = category.text

        setAdapter(category)

        binding.backArrow.popBackStack()
    }


    private fun setAdapter(categoryFilms: CategoryFilms) {
        viewLifecycleOwner.lifecycleScope.launch {

            viewModel.getFilms(categoryFilms).collect {
                adapter.submitData(it)
            }
        }
    }

}