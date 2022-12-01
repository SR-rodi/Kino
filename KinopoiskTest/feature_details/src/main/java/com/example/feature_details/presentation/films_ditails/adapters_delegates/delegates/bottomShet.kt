package com.example.feature_details.presentation.films_ditails.adapters_delegates.delegates

import com.example.core.tools.adapter.FilmsCollection
import com.example.feature_details.databinding.ItemCollectionFilmsBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun bottomSheetDelegate(onClickItem: (collection: FilmsCollection, isCheck:Boolean) -> Unit) =
    adapterDelegateViewBinding<FilmsCollection, FilmsCollection, ItemCollectionFilmsBinding>(
        { layoutInflater, root ->
            ItemCollectionFilmsBinding.inflate(layoutInflater, root, false)
        }) {
        binding.collection.setOnClickListener {
            item.isCheck = !item.isCheck
            onClickItem(item,item.isCheck)
        }
        bind {
            binding.collection.isChecked = item.isCheck
            binding.collection.text = item.nameCollection
            binding.listSize.text = item.size.toString()
        }
    }
