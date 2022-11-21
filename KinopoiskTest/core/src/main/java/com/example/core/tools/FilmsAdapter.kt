package com.example.core.tools

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.core.databinding.ItemFilmsBinding
import com.example.core.tools.all.BaseFilms

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