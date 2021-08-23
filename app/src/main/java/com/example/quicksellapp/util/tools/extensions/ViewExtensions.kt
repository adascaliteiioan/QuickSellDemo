package com.example.quicksellapp.util.tools.extensions

import android.view.View

fun View.translationBottomToTop() = run {
    with(this) {
        alpha = 0f
        translationY = 50f
        animate().alpha(1f).translationYBy(-50f).setDuration(1500)
    }
}