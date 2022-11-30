package com.example.feature_details.presentation.staff_details.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.core.tools.BaseFragment
import com.example.core.tools.FilmsAdapter
import com.example.core.tools.extensions.glide
import com.example.core.tools.extensions.popBackStack
import com.example.feature_details.databinding.FragmentStaffInfoBinding
import com.example.feature_details.presentation.staff_details.viewModel.StaffInfoViewModel
import kotlinx.coroutines.launch
import com.example.core.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class StaffInfoFragment : BaseFragment<FragmentStaffInfoBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentStaffInfoBinding.inflate(inflater)

    private val viewModel by viewModel<StaffInfoViewModel>()

    private val adapter by lazy { FilmsAdapter({}, {}) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getStaff()
        observe()

        binding.bestFilms.adapter = adapter

        binding.backArrow.popBackStack()

        binding.name.setOnClickListener {
            viewModel.navigateToFilmography()
            findNavController().navigate(R.id.action_filmInfoFragment_to_filmographyFragment)
        }

    }

    private fun observe() {

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.staff.collect {
                binding.avatar.glide(it.first.posterUrl)
                binding.name.text = it.first.nameRu
                binding.description.text = it.first.profession
                adapter.submitList(it.second)
            }
        }
    }

}