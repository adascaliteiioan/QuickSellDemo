package com.example.quicksellapp.ui.products

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.quicksellapp.data.DummyDataSource
import com.example.quicksellapp.data.db.ProductsDao
import com.example.quicksellapp.model.Product
import com.example.quicksellapp.model.ProductsCategory
import com.example.quicksellapp.util.tools.extensions.formatPrice
import com.example.quicksellapp.util.tools.prefs.PrefsManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class ProductsViewModel @Inject constructor(
    private val productsDao: ProductsDao,
    private val app: Application
) : AndroidViewModel(app) {

    private val orderedProductsMap = mutableMapOf<Product, Product>()

    private var _currentAmount = MutableStateFlow("0")
    val currentAmount: StateFlow<String> = _currentAmount

    fun getAllProducts(): Flow<List<ProductsCategory>> = flow {
        productsDao.getAllProducts().collect { products ->
            val productsCategory = mutableListOf<ProductsCategory>()
            DummyDataSource.categories.forEach { category ->
                val prod = ProductsCategory(
                    category.name,
                    products.filter { it.category == category.name })
                if (prod.products.isNotEmpty()) {
                    productsCategory.add(prod)
                }
            }
            emit(productsCategory)
        }
    }

    fun updateProductOrder(product: Product) {
        orderedProductsMap[product] = product
        calculateCurrentAmount()
    }

    fun canPay() = orderedProductsMap.values.sumOf { it.quantityOrdered * it.price } > 0

    private fun calculateCurrentAmount() {
        val currentTotal = orderedProductsMap.values.sumOf { it.quantityOrdered * it.price }
        _currentAmount.value = currentTotal.formatPrice(app)
    }

}