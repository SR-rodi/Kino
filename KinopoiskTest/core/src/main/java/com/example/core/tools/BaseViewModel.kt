package com.example.core.tools

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.core.tools.all.LoadState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.net.UnknownHostException

abstract class BaseViewModel :ViewModel(){
    protected val _loadState =
        MutableStateFlow(LoadState.LOADING)
    val loadState = _loadState.asStateFlow()

    protected val handler = CoroutineExceptionHandler { _, T ->
        Log.e("Kart","${T.message}")
        if (T != UnknownHostException())
            _loadState.value = LoadState.ERROR
    }
}