package com.example.homepage.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homepage.data.repository.NetworkRepositoryHomePage
import kotlinx.coroutines.launch

class HomeViewModel(
    private val networkRepository: NetworkRepositoryHomePage
) : ViewModel() {


    fun getPremieresInNetwork() {
        viewModelScope.launch {
            val a = networkRepository.getPremieresInNetwork(2022, "JANUARY")
            Log.e("Kart","${a.size}")
        }
    }

}