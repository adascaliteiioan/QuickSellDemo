package com.example.quicksellapp.data

import com.example.quicksellapp.model.Category

object DummyDataSource {

    val categories = listOf(
        Category(name = "Food"),
        Category(name = "Beverages"),
        Category(name = "Meat"),
        Category(name = "Vegetables"),
        Category(name = "Cleaning")
    )
}