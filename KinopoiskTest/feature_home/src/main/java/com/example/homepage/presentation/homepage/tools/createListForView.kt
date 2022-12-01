package com.example.homepage.presentation.homepage.tools

import com.example.core.tools.category.CategoryInfo
import com.example.core.tools.general.Country
import com.example.core.tools.general.Genre


fun CategoryInfo.createRandomCategory(counter: Country, genre: Genre): CategoryInfo {
    if (this == CategoryInfo.RANDOM) {
        this.apply {
            text = counter.info + " " + genre.info
            query.counterID = counter.id
            query.genreId = genre.id
        }

    }
    return this
}

fun CategoryInfo.createPrimersCategory(years: Int, month: Int): CategoryInfo {
    if (this == CategoryInfo.PREMIERS) {
        this.apply {
            query.year = years
            query.month = month.converterInMonth()
        }
    }
    return this
}

