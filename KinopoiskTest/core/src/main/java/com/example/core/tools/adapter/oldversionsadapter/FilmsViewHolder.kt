package com.example.core.tools.adapter.oldversionsadapter

import android.app.ActionBar
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.example.core.databinding.ItemFilmsBinding
import com.example.core.tools.base_model.films.BaseFilm
import com.example.core.tools.extensions.createName
import com.example.core.tools.extensions.glide

class FilmsViewHolder(private val binding: ItemFilmsBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindNextShow(clickNextButton: () -> Unit) {
        binding.apply {
            nextButton.isInvisible = false
            nextText.isInvisible = false
            rating.isInvisible = true

            poster.isInvisible = true
            filmsName.isInvisible = true
            genreName.isInvisible = true

            binding.iconLook.isVisible = false
            binding.fgLook.isVisible = false

        }

        binding.itemCard.setOnClickListener { clickNextButton() }

    }

    fun bindItem(
        item: BaseFilm,
        isCategoryFragment: Boolean = false,
        clickFilms: (id: Int) -> Unit,
    ) {


        if (isCategoryFragment) {
            binding.itemCard.layoutParams.width = ActionBar.LayoutParams.MATCH_PARENT
            binding.itemCard.setPadding(8)
        }


        binding.apply {
            nextButton.isInvisible = true
            nextText.isInvisible = true
            rating.isInvisible = false

            poster.isInvisible = false
            filmsName.isInvisible = false
            genreName.isInvisible = false

            filmsName.text = item.nameRu
            genreName.text = item.genres.createName()
            poster.glide(item.posterUrlPreview)
            if (item.rating != null) {
                rating.isInvisible = false
                rating.text = item.rating
            } else rating.isInvisible = true
            binding.iconLook.isVisible = item.isLook
            binding.fgLook.isVisible = item.isLook
        }
        binding.itemCard.setOnClickListener { clickFilms(item.filmId) }
    }

    fun bindStun() {
        binding.ooops.isInvisible = false
        binding.rating.isInvisible = true
    }

}