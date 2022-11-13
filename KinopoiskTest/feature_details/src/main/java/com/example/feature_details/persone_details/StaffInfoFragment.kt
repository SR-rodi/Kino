package com.example.feature_details.persone_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.core.tools.BaseFragment
import com.example.core.tools.extensions.glide
import com.example.feature_details.databinding.FragmentStaffInfoBinding
import com.example.feature_details.details_staff.ProfessionKey
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class StaffInfoFragment : BaseFragment<FragmentStaffInfoBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentStaffInfoBinding.inflate(inflater)

    private val viewModel by viewModel<StaffInfoViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        viewModel.getStaff(TEST_ID)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.films.collect {
                binding.pager.adapter = PageAdapter(this@StaffInfoFragment,it)
                setTab()

            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.staff.collect {
                binding.avatar.glide(it.posterUrl)
                binding.name.text = it.nameRu
                binding.description.text = it.profession

            }
        }

    }

    private fun setTab() {
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = ProfessionKey.values()[position].profession
        }.attach()
    }


    companion object {
        private const val TEST_ID = 66539
    }


}