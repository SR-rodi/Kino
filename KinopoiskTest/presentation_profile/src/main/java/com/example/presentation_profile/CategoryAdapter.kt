package com.example.presentation_profile

import android.util.Log
import androidx.core.view.isVisible
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.example.core.databinding.ItemCategoryBinding
import com.example.core.databinding.ItemFilmsBinding
import com.example.core.tools.CategoryFromAdapter
import com.example.core.tools.NestedCollection
import com.example.core.tools.NestedFilm
import com.example.core.tools.all.BaseEntityFilm
import com.example.core.tools.all.CategoryInfo
import com.example.core.tools.all.NestedInfoInCategory
import com.example.core.tools.extensions.glide
import com.example.core.tools.general.InfoFilms
import com.example.presentation_profile.databinding.ItemCollectionBinding
import com.hannesdorfmann.adapterdelegates4.ListDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

class CategoryListAdapter(
) : ListDelegationAdapter<List<InfoFilms>>() {
    init {
        delegatesManager.addDelegate(likeDelegate())
            .addDelegate(collectionCCCDelegate())

        setItems(items)
    }

}

class NestedAdapter(
) : ListDelegationAdapter<List<NestedInfoInCategory>>() {
    init {

        delegatesManager.addDelegate(filmsDelegate())
            .addDelegate(collectionsDelegate())

        setItems(items)
    }

}

fun likeDelegate() =
    adapterDelegateViewBinding<CategoryFromAdapter, InfoFilms, ItemCategoryBinding>({ layoutInflater, root ->
        ItemCategoryBinding.inflate(layoutInflater, root, false)
    }) {
        bind {
            Log.e("Kart", " test $item.toString()")
            val adapter = NestedAdapter()
            binding.categoryName.text = item.category
            adapter.items = item.listValue
            binding.filmsRecyclerView.adapter = adapter
        }
    }

fun collectionCCCDelegate() =
    adapterDelegateViewBinding<ProfileViewModel.CategoryCollection, InfoFilms, ItemCategoryBinding>(
        { layoutInflater, root ->
            ItemCategoryBinding.inflate(layoutInflater, root, false)
        }) {
        bind {
            binding.apply {
                categoryName.text = item.category
                filmsRecyclerView.layoutManager =
                    GridLayoutManager(
                        binding.filmsRecyclerView.context,
                        2,
                        GridLayoutManager.HORIZONTAL,
                        false
                    )
                filmsRecyclerView.adapter = NestedAdapter().apply {
                    items = item.list
                }
            }


        }
    }

fun filmsDelegate() =
    adapterDelegateViewBinding<NestedFilm, NestedInfoInCategory, ItemFilmsBinding>({ layoutInflater, root ->
        ItemFilmsBinding.inflate(layoutInflater, root, false)
    }) {


        bind {
            binding.poster.glide(item.posterUrlPreview)
            binding.filmsName.text = item.nameRu
            binding.genreName.text = item.genres.first().info
            binding.rating.text = item.rating
        }
    }


fun collectionsDelegate() =
    adapterDelegateViewBinding<NestedCollection, NestedInfoInCategory, ItemCollectionBinding>({ layoutInflater, root ->
        ItemCollectionBinding.inflate(layoutInflater, root, false)
    }) {


        bind {
            binding.collectionName.text = item.nameCollection
            binding.amountFilms.text = item.size.toString()
            binding.imageCollection.rotation =
                if (item.icon == com.example.core.R.drawable.ic_close) 45f
                else 0f
            binding.imageCollection.glide(item.icon)
            binding.deleteButton.isVisible =item.isDelete
        }
    }
