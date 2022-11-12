package com.example.homepage.presentation.categoryoage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.core.tools.BaseFragment
import com.example.homepage.databinding.FragmentCategoryPageBinding
import com.example.homepage.presentation.homepage.adapters.categorypage.FilmAdapterFromPage
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryPageFragment : BaseFragment<FragmentCategoryPageBinding>() {

    override fun initBinding(inflater: LayoutInflater) =
        FragmentCategoryPageBinding.inflate(inflater)

    private val args by navArgs<CategoryPageFragmentArgs>()

    private val viewModel by viewModel<CategoryPageViewModel>()

    private val adapter by lazy { FilmAdapterFromPage() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.categoryPageRecyclerView.adapter = adapter

         setAdapter()

            binding.categoryTitle.text = args.category.text
        binding.backArrow.setOnClickListener {
            findNavController().navigate(CategoryPageFragmentDirections.actionCategoryPageFragmentToFilmInfoFragment())
        }
    }


    private fun setAdapter() {
        viewLifecycleOwner.lifecycleScope.launch {

            viewModel.getFilms(args.category).collect {
                adapter.submitData(it)
            }
        }
    }

}