package com.example.screen_listpage.start

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.paging.map
import com.example.core.tools.BaseFragment
import com.example.core.tools.extensions.setTextChangesFlowListener
import com.example.screen_listpage.R
import com.example.screen_listpage.databinding.FragmentSearchStartBinding
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchStartFragment : BaseFragment<FragmentSearchStartBinding>() {
    override fun initBinding(inflater: LayoutInflater) = FragmentSearchStartBinding.inflate(inflater)


    private val viewModel by viewModel<SearchStartViewModel>()

    private val adapter = FilmsAdapter()

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        observe()

        super.onViewCreated(view, savedInstanceState)
        binding.settings.setEndIconOnClickListener{
            Toast.makeText(requireContext(), "нажал", Toast.LENGTH_SHORT).show()
       }

        binding.editText.setTextChangesFlowListener().let { viewModel.search(it) }

    }

    private fun observe(){
        viewLifecycleOwner.lifecycleScope.launch {
          viewModel.test.collect{
              adapter.submitData(it)
          }
        }
    }

}