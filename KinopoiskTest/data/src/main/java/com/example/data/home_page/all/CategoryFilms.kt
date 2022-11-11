package com.example.data.home_page.all

import com.example.data.home_page.all.Query


enum class CategoryFilms(var text :String,val query: Query) {
    BEST("ТОП-250", Query()),
    POPULAR("Популярное", Query()),
    PREMIERS("Премьеры", Query()),
    RANDOM("Исправить", Query())

}