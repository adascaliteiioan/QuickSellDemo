package com.example.quicksellapp.repository

import com.example.quicksellapp.data.DummyDataSource
import com.example.quicksellapp.model.Category
import com.example.quicksellapp.util.Resource
import kotlinx.coroutines.flow.flow

class QuickSellRepository {

    fun getCategories() = flow<Resource<List<Category>>> {
        val categoriesResource = Resource.Success(DummyDataSource.categories)
        emit(categoriesResource)
    }
}