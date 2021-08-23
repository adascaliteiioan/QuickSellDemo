package com.example.quicksellapp.util.tools.locale

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import java.util.*

object LocaleManager {

    fun setLocale(
        context: Context,
        language: String
    ): Context =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            updateResources(context, language)
        } else updateResourcesLegacy(context, language)


    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(
        context: Context,
        language: String
    ): Context =
        Locale(language).let { locale ->
            Locale.setDefault(locale)
            context.resources.configuration.apply {
                setLocale(locale)
                setLayoutDirection(locale)
            }.let {
                context.createConfigurationContext(it)
            }
        }


    private fun updateResourcesLegacy(
        context: Context,
        language: String
    ): Context =
        Locale(language).let { newLocale ->
            Locale.setDefault(newLocale)
            context.apply {
                resources.configuration.apply {
                    locale = newLocale
                    setLayoutDirection(newLocale)
                }.also {
                    resources.updateConfiguration(it, resources.displayMetrics)
                }
            }
        }
}