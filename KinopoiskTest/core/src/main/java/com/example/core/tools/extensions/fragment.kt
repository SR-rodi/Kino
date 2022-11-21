package com.example.core.tools.extensions

import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.core.R
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

@ExperimentalCoroutinesApi
fun EditText.setTextChangesFlowListener(): Flow<String> {
    return callbackFlow {
//        создаем textChangeListener
        val textChangeListener = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
//                при изменении текста отправляем текст
                trySendBlocking(s?.toString().orEmpty())
            }

            override fun afterTextChanged(s: Editable?) {}
        }
//        добавляем лисенер
        this@setTextChangesFlowListener.addTextChangedListener(textChangeListener)
//        при закрытии поля удаляем лисенер
        awaitClose {
            this@setTextChangesFlowListener.removeTextChangedListener(textChangeListener)
        }
    }
}