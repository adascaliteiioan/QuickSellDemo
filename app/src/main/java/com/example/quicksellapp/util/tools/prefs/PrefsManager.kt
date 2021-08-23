package com.example.quicksellapp.util.tools.prefs

import android.content.Context
import com.example.quicksellapp.model.LanguageType

class PrefsManager (context: Context) {
    companion object {
        private const val quickSellPreferences = "quicksell_preferences"
        private const val languageKey = "language_key"
    }

    private val sharedPrefs =
        context.getSharedPreferences(quickSellPreferences, Context.MODE_PRIVATE)

    var language: LanguageType = LanguageType.Eng
        get() = sharedPrefs.run {
            if (contains(languageKey)) LanguageType.valueOf(
                getString(languageKey, LanguageType.Eng.name) ?: LanguageType.Eng.name
            ) else LanguageType.Eng
        }
        set(value) {
            sharedPrefs.edit().putString(languageKey, value.name).apply()
            field = value
        }
}