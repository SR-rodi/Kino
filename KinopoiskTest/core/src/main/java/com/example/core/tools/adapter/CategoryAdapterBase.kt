package com.example.core.tools.adapter

import com.example.core.databinding.ItemCategoryBinding
import com.example.core.tools.adapter.diffutil.CategoryDiffUtil
import com.example.core.tools.base_model.category.BaseCategory
import com.example.core.tools.base_model.category.StartCategory
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

class CategoryAdapterBase(
    private val onClickItem: (id: Int) -> Unit,
    private val onClickAll: (category: BaseCategory) -> Unit,
) : AsyncListDifferDelegationAdapter<BaseCategory>(CategoryDiffUtil()) {

    init {
        delegatesManager.addDelegate(delegateHomeCategory({ onClickItem(it) }, { onClickAll(it) }))
    }

    companion object {
        fun delegateHomeCategory(
            onClickItem: (id: Int) -> Unit,
            onClickAll: (category: BaseCategory) -> Unit,
        ) = adapterDelegateViewBinding<StartCategory, BaseCategory, ItemCategoryBinding>({ layoutInflater, root ->
                ItemCategoryBinding.inflate(layoutInflater, root, false)
            }) {

                binding.buttonAll.setOnClickListener {
                    onClickAll(item)
                }

                bind {
                    binding.categoryName.text = item.category.text
                    binding.filmsRecyclerView.adapter =
                        NestedAdapterBase ({ onClickItem(it) },{onClickAll(it)},item).apply {
                            items = item.list
                        }
                }
            }
    }
}
