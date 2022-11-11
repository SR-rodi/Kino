package com.example.core.tools.extensions

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.core.R

fun ImageView.glide(imageUrl: String) {
    Glide.with(this)
        .load(imageUrl)
        .placeholder(R.drawable.ic_baseline_downloading_24)
        .error(R.drawable.ic_error)
        .into(this)
}