package com.example.quicksellapp.util.tools.extensions

import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment

fun Fragment.restartApp() {
    activity?.baseContext?.let { baseCtx ->
        baseCtx.packageManager.getLaunchIntentForPackage(baseCtx.packageName)?.apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }.also {
            startActivity(it)
            activity?.finish()
        }
    }
}

fun Fragment.showDialog(title: String, message: String) {
    AlertDialog.Builder(requireContext())
        .setTitle(title)
        .setMessage(message)
        .setPositiveButton("Ok", null)
        .create()
        .show()
}