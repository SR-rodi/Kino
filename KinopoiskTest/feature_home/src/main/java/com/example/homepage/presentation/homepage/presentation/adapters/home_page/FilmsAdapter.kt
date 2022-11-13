package com.example.homepage.presentation.homepage.presentation.adapters.home_page

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.core.databinding.ItemFilmsBinding
import com.example.homepage.presentation.homepage.presentation.adapters.Diff
import com.example.homepage.presentation.homepage.data.films.BaseFilms
import com.example.homepage.presentation.homepage.presentation.adapters.viewholder.FilmsViewHolder

class FilmsAdapter(
    private val clickNextButton: () -> Unit,
    private val clickFilms: (filmID: Int) -> Unit
) : ListAdapter<BaseFilms, FilmsViewHolder>(Diff()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = FilmsViewHolder(
        ItemFilmsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: FilmsViewHolder, position: Int) {
        if (position == itemCount - 1) holder.bindNextShow { clickNextButton() }
        else holder.bindItem(getItem(position)) { clickFilms(it) }
    }
}