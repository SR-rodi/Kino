package com.example.presentation_profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.core.tools.BaseFragment
import com.example.core.tools.CategoryFromAdapter
import com.example.core.tools.extensions.glide
import com.example.presentation_profile.databinding.FragmentProfileBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentProfileBinding.inflate(inflater)

    private val viewModel by viewModel<ProfileViewModel>()

   private val adapter = CategoryListAdapter()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCollection()
        observe()

    }

    private fun observe() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.profile.collect {
                Log.d("Kart","в адаптер $it")
                adapter.items = it
                binding.recyclerView.adapter = adapter
            }
        }
    }
}

