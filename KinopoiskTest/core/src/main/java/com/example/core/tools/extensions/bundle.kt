package com.example.core.tools.extensions

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.core.tools.CATEGORY_BUNDLE
import com.example.core.tools.CATEGORY_INFO_BUNDLE
import com.example.core.tools.INT_BUNDLE
import com.example.core.tools.all.CategoryFilms
import com.example.core.tools.all.CategoryInfo


fun CategoryFilms.createBundle(tag: String = CATEGORY_BUNDLE): Bundle {
    val categoryFilms = this //магия, ставлю this сразу в банд все падает
    return Bundle().apply { putParcelable(tag, categoryFilms) }
}

fun Int.createBundle(tag: String = INT_BUNDLE): Bundle {
    val int = this //магия, ставлю this сразу в банд все падает
    return Bundle().apply { putInt(tag, int) }
}

fun CategoryInfo.createBundle(): Bundle {
    val categoryInfo = this
    return Bundle().apply { putParcelable(CATEGORY_INFO_BUNDLE, categoryInfo) }
}


@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun Fragment.getArgsCategoryFilms(tag: String = CATEGORY_BUNDLE): CategoryFilms {
    val categoryFilms = arguments?.getParcelable<CategoryFilms>(tag)
    return categoryFilms!!
}

fun Fragment.getArgsInt(tag: String = INT_BUNDLE): Int {
    val int = arguments?.getInt(tag)
    return int!!
}

fun Fragment.getArgsCategoryInfo(tag: String = CATEGORY_INFO_BUNDLE): CategoryInfo {
    val categoryInfo = arguments?.getParcelable<CategoryInfo>(tag)
    return categoryInfo!!
}

fun Fragment.checkFirstStart(): Boolean {
    val pref = requireContext().getSharedPreferences("Pref_firstStart", Context.MODE_PRIVATE)

    val a = pref.contains("isFirstStart")

    if (!a) pref.edit().putBoolean("isFirstStart", false).apply()
    return !a
}
