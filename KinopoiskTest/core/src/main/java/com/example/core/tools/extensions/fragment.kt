package com.example.core.tools.extensions

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.core.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.error_dialog.view.*
import kotlinx.android.synthetic.main.item_films.view.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

fun <T : Fragment> T.withArguments(action: Bundle.() -> Unit): T {
    return apply {
        val args = Bundle().apply(action)
        arguments = args
    }
}

fun Fragment.createErrorDialog() {
    val dialog = LayoutInflater.from(requireContext())
        .inflate(R.layout.error_dialog, null)
    val closeButton = dialog.close_button
    closeButton.setOnClickListener {}

    AlertDialog.Builder(requireContext())
        .setView(dialog)
        .setOnDismissListener { }
        .create()
        .show()
}