package com.example.homepage.presentation.homepage.presentation.category_fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.RequiresApi
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.core.tools.BaseFragment
import com.example.core.tools.all.CategoryFilms
import com.example.core.tools.extensions.createBundle
import com.example.core.tools.extensions.createErrorDialog
import com.example.core.tools.extensions.getArgsCategoryFilms
import com.example.core.tools.extensions.popBackStack
import com.example.homepage.R
import com.example.homepage.databinding.FragmentCategoryPageBinding
import com.example.homepage.presentation.homepage.presentation.adapters.filmsAdapter.FilmAdapterFromPage
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryPageFragment : BaseFragment<FragmentCategoryPageBinding>() {

    override fun initBinding(inflater: LayoutInflater) =
        FragmentCategoryPageBinding.inflate(inflater)

    private val viewModel by viewModel<CategoryPageViewModel>()

    private val adapter by lazy { FilmAdapterFromPage{onClickItem(it)} }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.categoryPageRecyclerView.adapter = adapter

        val category = getArgsCategoryFilms()

        binding.categoryTitle.text = category.text

        setAdapter(category)

        loadStateListener()

        binding.backArrow.popBackStack()

    }

    private fun onClickItem(filmId:Int){
        findNavController().navigate(com.example.core.R.id.action_categoryPageFragment_to_filmInfoFragment,filmId.createBundle())
    }

    private fun loadStateListener() {
        adapter.addLoadStateListener {
            binding.progressBar.isVisible = it.refresh is LoadState.Loading
            if (it.source.refresh is LoadState.Error) createErrorDialog()
        }
    }

    private fun setAdapter(categoryFilms: CategoryFilms) {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getFilms(categoryFilms).collect {
                adapter.submitData(it)
            }
        }
    }
}