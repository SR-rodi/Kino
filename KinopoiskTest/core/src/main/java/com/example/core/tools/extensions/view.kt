package com.example.core.tools.extensions

import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.core.R
import com.example.core.tools.CATEGORY_BUNDLE
import com.example.core.tools.INT_BUNDLE
import com.example.core.tools.all.CategoryFilms
import com.example.core.tools.general.GenreCountry


fun ImageView.glide(imageUrl: String) {
    Glide.with(this)
        .load(imageUrl)
        .placeholder(R.drawable.ic_baseline_downloading_24)
        .error(R.drawable.ic_error)
        .into(this)
}

fun ImageView.popBackStack() {
    this.setOnClickListener {
        findNavController().popBackStack()
    }
}

fun List<GenreCountry>.createName(): String {
    var genreName = ""
    this.forEachIndexed { index, info ->
        genreName += if (index == this.lastIndex) info.info
        else "${info.info}, "
    }
    return genreName
}

fun CategoryFilms.createBundle(tag: String = CATEGORY_BUNDLE): Bundle {
    val categoryFilms = this //магия, ставлю this сразу в банд все падает
    return Bundle().apply { putParcelable(tag, categoryFilms) }
}

fun Int.createBundle(tag: String = INT_BUNDLE): Bundle {
    val int = this //магия, ставлю this сразу в банд все падает
    return Bundle().apply { putInt(tag, int) }
}

fun Fragment.getArgsCategoryFilms(tag: String = CATEGORY_BUNDLE): CategoryFilms {
    val categoryFilms = arguments?.getParcelable<CategoryFilms>(tag)
    return categoryFilms!!
}

fun Fragment.getArgsInt(tag: String = INT_BUNDLE): Int {
    val int = arguments?.getInt(tag)
    return int!!
}