package com.example.homepage.presentation.homepage.viewholder

import android.app.ActionBar.LayoutParams
import androidx.core.view.isInvisible
import androidx.core.view.setPadding
import androidx.recyclerview.widget.RecyclerView
import com.example.core.databinding.ItemFilmsBinding
import com.example.core.tools.extensions.createName
import com.example.core.tools.extensions.glide
import com.example.data.home_page.films.BaseFilms


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

    fun bindItem(item: BaseFilms, isCategoryFragment:Boolean = false, clickFilms:(id:Int)->Unit,) {

        if (isCategoryFragment){
             binding.item.layoutParams.width = LayoutParams.MATCH_PARENT
            binding.item.setPadding(8)
        }


        binding.apply {
            filmsName.text = item.nameRu
            genreName.text = item.genres.createName()
            poster.glide(item.posterUrlPreview)
            if (item.rating != null) {
                rating.isInvisible = false
                rating.text = item.rating
            }
            else  rating.isInvisible = true
        }
        binding.item.setOnClickListener{ clickFilms(item.filmId) }
    }

}
