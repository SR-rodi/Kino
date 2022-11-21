package com.example.feature_details.presentation.films_ditails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.core.tools.extensions.popBackStack
import com.example.feature_details.databinding.BottomSheetMenuBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetMenuFragment: BottomSheetDialogFragment() {
    private var _binding: BottomSheetMenuBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = BottomSheetMenuBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imageFilterView.setOnClickListener{
            findNavController().popBackStack()
        }
    }
}