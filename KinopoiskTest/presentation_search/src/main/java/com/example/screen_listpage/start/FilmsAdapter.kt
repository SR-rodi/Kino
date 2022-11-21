package com.example.screen_listpage.start

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.core.tools.Diff
import com.example.core.tools.all.BaseFilms
import com.example.core.tools.extensions.glide
import com.example.screen_listpage.data.Film
import com.example.screen_listpage.databinding.ItemFilmsSearchBinding

class FilmsAdapter: PagingDataAdapter<Film, SearchFilmsViewHolder>(NewDiff()) {

    override fun onBindViewHolder(holder: SearchFilmsViewHolder, position: Int) {
        Log.d("Kart","${getItem(position)}" )
        holder.bind(getItem(position) as Film)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= SearchFilmsViewHolder(
        ItemFilmsSearchBinding.inflate(LayoutInflater.from(parent.context),parent,false)
    )


}

class SearchFilmsViewHolder (private val binding:ItemFilmsSearchBinding):RecyclerView.ViewHolder(binding.root){

    fun bind(item:Film){

            binding.poster.glide(item.posterUrl)
    }
}

class NewDiff:DiffUtil.ItemCallback<Film>(){
    override fun areItemsTheSame(oldItem: Film, newItem: Film) = oldItem.filmId == newItem.filmId

    override fun areContentsTheSame(oldItem: Film, newItem: Film) = oldItem.posterUrl ==newItem.posterUrl
}