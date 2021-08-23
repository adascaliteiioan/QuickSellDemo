package com.example.quicksellapp.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quicksellapp.model.LanguageType
import com.example.quicksellapp.repository.LanguageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val app: Application,
    private val languageRepository: LanguageRepository
) : AndroidViewModel(app) {

    fun setupLanguage(languageType: LanguageType) {
        languageRepository.updateLanguage(app, languageType)
    }
}