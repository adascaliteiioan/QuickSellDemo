package com.example.quicksellapp.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class Product(
    val name: String,
    val brand: String,
    val category: String,
    val quantity: Int,
//    val photos: List<String>? = null,
    val price: Long,
    val description: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long? = null

    @Ignore
    var quantityOrdered: Int = 0
        set(value) {
            field = when {
                value < 0 -> 0
                value > quantity -> quantity
                else -> value
            }
        }
}