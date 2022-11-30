package com.example.core.tools.extensions

import android.app.AlertDialog
import android.content.Intent
import android.content.res.Resources
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.core.R
import com.example.core.databinding.ItemPosterInfoBinding
import com.example.core.tools.CATEGORY_BUNDLE
import com.example.core.tools.CATEGORY_INFO_BUNDLE
import com.example.core.tools.INT_BUNDLE
import com.example.core.tools.all.CategoryFilms
import com.example.core.tools.all.CategoryInfo
import com.example.core.tools.general.GenreCountry


fun ImageView.glide(imageUrl: String) {
    Glide.with(this)
        .load(imageUrl)
        .placeholder(R.drawable.ic_baseline_downloading_24)
        .error(R.drawable.ic_error)
        .into(this)
}

fun ImageView.glide(recurseID: Int) {
    Glide.with(this)
        .load(recurseID)
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

fun ImageView.clickAndShowShareDialog(share: String) {

    this.setOnClickListener {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, share)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(this.context,shareIntent,null)
    }
}