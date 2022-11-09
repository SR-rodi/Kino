package com.example.homepage.presentation.viewholder

import androidx.core.view.isInvisible
import androidx.recyclerview.widget.RecyclerView
import com.example.core.databinding.ItemFilmsBinding
import com.example.kinopoisk_api.domine.all.Genre
import com.example.kinopoisk_api.domine.home_page.HomePageItem
import com.example.core.tools.glide


class FilmsViewHolder(private val binding: ItemFilmsBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bindNextShow(clickNextButton:()->Unit) {
        binding.apply {
            nextButton.isInvisible = false
            nextText.isInvisible = false
            rating.isInvisible = true
        }

        binding.item.setOnClickListener{ clickNextButton() }

    }

    fun bindItem(item: HomePageItem, clickFilms:(nameFilms:String)->Unit) {

        binding.apply {
            filmsName.text = item.nameRu
            genreName.text = createGenreName(item.genres)
            poster.glide(item.posterUrlPreview)
            if (item.rating != null) {
                rating.isInvisible = false
                rating.text = item.rating
            }
            else  rating.isInvisible = true
        }
        binding.item.setOnClickListener{ clickFilms(item.nameRu) }
    }

    private fun createGenreName(genres: List<Genre>): String {
        var genreName = ""
        genres.forEachIndexed { index, genre ->
            genreName += if (index == genres.lastIndex) genre.genre
            else "${genre.genre}, "
        }
        return genreName
    }
}
