package com.example.quicksellapp.util.tools.extensions

import android.content.Context
import com.example.quicksellapp.util.QuickSellCurrency
import com.example.quicksellapp.util.formatPrice
import com.example.quicksellapp.util.tools.prefs.PrefsManager

fun Long.formatPrice(context: Context) : String {
    val currency = QuickSellCurrency.fromLanguage(PrefsManager(context).language)
    val finalPrice = currency.formatPrice(this)
    return "$finalPrice ${currency.currency}"
}