package com.sahonmu.burger87.extension

import android.app.Activity
import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart
import kotlin.math.roundToInt

fun Context.dpToPx(dp: Float): Int {
    val density = this.resources.displayMetrics.density
    return (dp * density).roundToInt()
}

fun Context.dimensToInt(resource: Int): Int {
    return this.resources.getDimensionPixelSize(resource)
}

fun Context.dimensToFloat(resource: Int): Float {
    return this.resources.getDimension(resource)
}

fun Context.color(resource: Int): Int {
    return ContextCompat.getColor(this, resource)
}

fun Activity.color(resource: Int): Int {
    return ContextCompat.getColor(this, resource)
}

fun EditText.textChanges(): Flow<CharSequence?> {
    return callbackFlow {
        val listener = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) { trySend(s) }
        }
        addTextChangedListener(listener)
        awaitClose { removeTextChangedListener(listener) }
    }.onStart { emit(text) }
}

fun TextView.pretty(text: String) {
    this.text = text.replace(" ", "\u00A0")
}

fun View.setMargins(
    start: Int? = null,
    top: Int? = null,
    end: Int? = null,
    bottom: Int? = null,
) {
    if (layoutParams is ViewGroup.MarginLayoutParams) {
        val params = layoutParams as ViewGroup.MarginLayoutParams
        start?.run { params.leftMargin = this }
        top?.run { params.topMargin = this }
        end?.run { params.rightMargin = this }
        bottom?.run { params.bottomMargin = this }
        requestLayout()
    }
}

fun EditText.hideKeyboard() {
    this.clearFocus()
    this.postDelayed({
        val imm =
            context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(this.windowToken, 0)
    }, 50)
}
