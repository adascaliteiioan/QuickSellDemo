package com.example.quicksellapp.repository

import android.content.Context
import com.example.quicksellapp.model.LanguageType
import com.example.quicksellapp.util.tools.locale.LocaleManager
import com.example.quicksellapp.util.tools.prefs.PrefsManager
import javax.inject.Inject

class LanguageRepository @Inject constructor(
    private val prefsManager: PrefsManager
) {

    fun updateLanguage(context: Context, languageType: LanguageType): Context {
        prefsManager.language = languageType
        return LocaleManager.setLocale(context, languageType.locale)
    }
}