package com.example.quicksellapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.quicksellapp.model.Product

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class ProductDb : RoomDatabase() {

    abstract fun productDao(): ProductsDao
}