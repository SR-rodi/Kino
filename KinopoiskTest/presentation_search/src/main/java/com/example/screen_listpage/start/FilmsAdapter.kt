package com.example.screen_listpage.start

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.core.tools.adapter.diffutil.CategoryDiffUtil
import com.example.core.tools.adapter.oldversionsadapter.BaseFilmsDiff
import com.example.core.tools.base_model.films.BaseFilm
import com.example.core.tools.extensions.createName
import com.example.core.tools.extensions.glide
import com.example.screen_listpage.data.SearchFilm
import com.example.screen_listpage.databinding.ItemFilmsSearchBinding

class FilmsAdapter : ListAdapter<BaseFilm, SearchFilmsViewHolder>(BaseFilmsDiff()) {

    override fun onBindViewHolder(holder: SearchFilmsViewHolder, position: Int) {
        holder.bind(getItem(position) as SearchFilm)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = SearchFilmsViewHolder(
        ItemFilmsSearchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

}

class SearchFilmsViewHolder(private val binding: ItemFilmsSearchBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: SearchFilm) {

        binding.poster.glide(item.posterUrl)
        binding.name.text = item.nameEn ?: item.nameRu
        binding.description.text = item.year.toString() + ", " + item.genres.createName()
        binding.rating.text =item.rating
    }
}