package com.example.screen_listpage

import android.view.LayoutInflater
import androidx.navigation.fragment.navArgs
import com.example.core.tools.BaseFragment
import com.example.screen_listpage.databinding.FragmentCategoryPageBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CategoryPageFragment : BaseFragment<FragmentCategoryPageBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentCategoryPageBinding.inflate(inflater)

    val viewModel by viewModel<CategoryPageViewModel>()

}