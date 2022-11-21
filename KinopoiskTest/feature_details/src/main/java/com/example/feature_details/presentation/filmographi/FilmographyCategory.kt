package com.example.feature_details.presentation.filmographi

import android.os.Parcelable
import com.example.feature_details.data.details_staff.Film
import kotlinx.android.parcel.Parcelize


enum class FilmographyCategory(
    val nameCategory: String,
    var items: MutableList<Film> = mutableListOf()
)  {
    WRITER("Писатель"),
    OPERATOR("Оператор"),
    EDITOR("Редактор"),
    COMPOSER("Композитор"),
    PRODUCER_USSR("Продюссер"),
    HIMSELF("Играет свм себя"),
    HERSELF("Играет саму себя"),
    HRONO_TITR_MALE("что?"),
    HRONO_TITR_FEMALE("что?"),
    TRANSLATOR("Переводчик"),
    DIRECTOR("Директор"),
    DESIGN("Дизайнер"),
    PRODUCER("Продюссер"),
    ACTOR("Актер"),
    VOICE_DIRECTOR("Звуко режесёр"),
    UNKNOWN("Остальное")
}