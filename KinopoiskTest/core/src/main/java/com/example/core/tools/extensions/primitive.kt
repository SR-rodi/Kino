package com.example.core.tools.extensions

import com.example.data.home_page.all.Month


fun Int.converterInMonth(): String {
    var textMonth = ""
    if (this <= 0 || this > 12)
        textMonth = Month.AUGUST.name
    else
        Month.values().forEach { month ->
            if (this == month.count) textMonth = month.name
        }
    return textMonth
}



