package com.example.quicksellapp.ui.quicksell

import androidx.lifecycle.ViewModel
import com.example.quicksellapp.model.Category
import com.example.quicksellapp.repository.QuickSellRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class QuickSellViewModel @Inject constructor(
    private val quickSellRepository: QuickSellRepository
) : ViewModel() {

    private var _photosList = MutableStateFlow(mutableListOf<String>())
    val photoList: StateFlow<List<String>> = _photosList

    var selectedCategory : Category? = null

    fun getCategories() = quickSellRepository.getCategories()

    fun addPhoto(photo: String) {
        val oldList = _photosList.value
        val newList = mutableListOf<String>()
        newList.addAll(oldList)
        newList.add(photo)
        _photosList.value = newList
    }

    fun removePhoto(photo: String) {
        val oldList = _photosList.value
        val newList = mutableListOf<String>()
        newList.addAll(oldList)
        newList.remove(photo)
        _photosList.value = newList
    }
}