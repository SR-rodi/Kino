package com.example.screen_listpage.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.example.core.tools.BaseFragment
import com.example.screen_listpage.databinding.FragmentYearPickerBinding
import kotlinx.coroutines.launch

class YearPickerFragment : BaseFragment<FragmentYearPickerBinding>() {
    override fun initBinding(inflater: LayoutInflater) = FragmentYearPickerBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var dateFrom = 1000
        var dateto = 3000

        binding.rangeFrom.setDateListener = { date ->
            dateFrom = date
            binding.buttonSeleckt.isEnabled = dateFrom < dateto
            Toast.makeText(requireContext(), "выбрана c $date ", Toast.LENGTH_SHORT).show()
        }

        binding.rangeTo.setDateListener = { date ->
            dateto = date
            binding.buttonSeleckt.isEnabled = dateFrom < dateto
            Toast.makeText(requireContext(), "выбрана до $date ", Toast.LENGTH_SHORT).show()
        }

    }
}