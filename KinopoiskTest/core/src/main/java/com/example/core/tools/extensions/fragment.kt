package com.example.core.tools.extensions

import android.app.AlertDialog
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.core.R
import com.example.core.tools.adapter.home.NestedAdapterBase
import com.example.core.tools.all.LoadState
import com.example.core.tools.all.NestedInfoInCategory
import com.example.core.tools.category.TypeSettings
import com.example.core.tools.general.GenreCountry
import kotlinx.android.synthetic.main.add_dialog.view.*
import kotlinx.android.synthetic.main.error_dialog.view.close_button
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


fun Fragment.createErrorDialog() {
    val view = LayoutInflater.from(requireContext())
        .inflate(R.layout.error_dialog, null)
    val closeButton = view.close_button


    val dialog = AlertDialog.Builder(requireContext())
        .setView(view)
        .create()

    closeButton.setOnClickListener { dialog.dismiss() }

    dialog.show()
}

fun Fragment.createAddDialog(addCollection: (name: String) -> Unit) {
    val view = LayoutInflater.from(requireContext())
        .inflate(R.layout.add_dialog, null)
    val positiveButtonClickListener = view.positive_button
    val negativeButton = view.close_button
    val editText = view.edit_text

    val dialog = AlertDialog.Builder(requireContext())
        .setView(view)
        .create()

    positiveButtonClickListener.setOnClickListener {
        addCollection(editText.text.toString())
        dialog.dismiss()
    }

    negativeButton.setOnClickListener { dialog.dismiss() }

    dialog.show()

}

fun Fragment.observeLoadState(
    stateFlow: StateFlow<LoadState>,
    progressBar: View,
    block: () -> Unit
) {
    viewLifecycleOwner.lifecycleScope.launch {
        stateFlow.collect { state ->
            when (state) {
                LoadState.LOADING -> progressBar.isVisible = true
                LoadState.NOT_RESULT -> block()
                LoadState.SUCCESS -> progressBar.isVisible = false
                LoadState.ERROR -> createErrorDialog()
            }
        }
    }
}