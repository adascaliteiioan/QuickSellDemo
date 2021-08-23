package com.example.quicksellapp.util

import com.example.quicksellapp.model.LanguageType

enum class QuickSellCurrency(val currency : String, val conversionValue : Double) {
    RON("Lei", 4.9),
    EUR ("Eur", 1.0);

    companion object {
        fun fromLanguage(langType : LanguageType) =
            when (langType) {
                LanguageType.Ro -> RON
                LanguageType.Eng -> EUR
            }
    }
}

fun QuickSellCurrency.formatPrice(price : Long) = price * conversionValue
