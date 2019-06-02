package com.exercise.album.ext

import android.view.View
import com.google.android.material.snackbar.Snackbar

inline fun View.show() {
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
}

inline fun View.gone() {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
}

inline fun View.snack(messageResId: Int) = Snackbar.make(this, messageResId, Snackbar.LENGTH_SHORT).show()