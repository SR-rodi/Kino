package com.example.screen_listpage.presentation.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.feature_database.repository.DataBaseRepository
import com.example.core.tools.SetSearch
import com.example.screen_listpage.data.SettingData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class SettingsSearchViewModel(
    private val dataBase: DataBaseRepository
) : ViewModel() {

    private val _data = MutableSharedFlow<SettingData>()
    val data = _data.asSharedFlow()


    init {
        getDataList()
    }

    fun getDataList() =
        viewModelScope.launch(Dispatchers.IO) {

            _data.emit(
                SettingData( dataBase.getAllCounter(),
                    dataBase.getAllGenre(),
                    dataBase.genCountryNameById(SetSearch.counterID),
                    dataBase.getGenreNameByID(SetSearch.genreId),
                )
            )
        }
}
