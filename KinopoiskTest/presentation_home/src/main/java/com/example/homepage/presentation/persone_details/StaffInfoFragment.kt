package com.example.homepage.presentation.persone_details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.core.tools.BaseFragment
import com.example.homepage.R
import com.example.homepage.databinding.FragmentStaffInfoBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class StaffInfoFragment : BaseFragment<FragmentStaffInfoBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentStaffInfoBinding.inflate(inflater)

    private val viewModel by viewModel<StaffInfoViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getStaff(TEST_ID)

    }

    companion object {
        private const val TEST_ID = 66539
    }



}