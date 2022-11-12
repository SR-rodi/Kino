package com.example.homepage.presentation.persone_details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.core.tools.BaseFragment
import com.example.core.tools.extensions.glide
import com.example.homepage.R
import com.example.homepage.databinding.FragmentStaffInfoBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class StaffInfoFragment : BaseFragment<FragmentStaffInfoBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentStaffInfoBinding.inflate(inflater)

    private val viewModel by viewModel<StaffInfoViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getStaff(TEST_ID)

        viewLifecycleOwner.lifecycleScope.launch{

            viewModel.staff.collect{
                binding.avatar.glide(it.posterUrl)
                binding.countFilms.text = it.films.size.toString()
                binding.name.text = it.nameRu
            }
        }

    }

    companion object {
        private const val TEST_ID = 66539
    }



}