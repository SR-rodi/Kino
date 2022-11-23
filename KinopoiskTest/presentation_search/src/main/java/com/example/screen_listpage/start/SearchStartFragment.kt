package com.example.screen_listpage.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.core.tools.BaseFragment
import com.example.screen_listpage.databinding.FragmentSearchStartBinding
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchStartFragment : BaseFragment<FragmentSearchStartBinding>() {
    override fun initBinding(inflater: LayoutInflater) =
        FragmentSearchStartBinding.inflate(inflater)

    private val viewModel by viewModel<SearchStartViewModel>()

    private val adapter = FilmsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        observeInput()

        super.onViewCreated(view, savedInstanceState)
        binding.settings.setEndIconOnClickListener {
            Toast.makeText(requireContext(), "нажал", Toast.LENGTH_SHORT).show()
            findNavController().navigate(com.example.core.R.id.action_searchFragment_to_settingsSearchFragment)
        }
        binding.editText.doOnTextChanged { text, _, _, after ->
            viewModel.search(text.toString(), after)
        }

        binding.searchRecyclerView.adapter = adapter

    }

    private fun observeInput() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.films.collect {
                adapter.submitList(it)
            }
        }
    }
}