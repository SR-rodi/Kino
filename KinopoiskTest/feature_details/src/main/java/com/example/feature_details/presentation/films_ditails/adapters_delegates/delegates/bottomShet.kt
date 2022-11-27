package com.example.feature_details.presentation.films_ditails.adapters_delegates.delegates

import com.example.feature_database.entity.FilmsCollectionEntity
import com.example.feature_details.databinding.ItemCollectionFilmsBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun bottomSheetDelegate(onClickItem: (collection:FilmsCollectionEntity) -> Unit) =
    adapterDelegateViewBinding<FilmsCollectionEntity, FilmsCollectionEntity, ItemCollectionFilmsBinding>(
        { layoutInflater, root ->
            ItemCollectionFilmsBinding.inflate(layoutInflater, root, false)
        }) {
        binding.collection.setOnClickListener{
            onClickItem(item)
        }
        bind {
            binding.collection.text = item.nameCollection
            binding.listSize.text = item.size.toString()
        }
    }
