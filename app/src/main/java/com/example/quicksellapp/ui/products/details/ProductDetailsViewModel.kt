package com.example.quicksellapp.ui.products.details

import androidx.lifecycle.ViewModel
import com.example.quicksellapp.data.db.ProductsDao
import com.example.quicksellapp.model.Product
import com.example.quicksellapp.util.QuickSellCurrency
import com.example.quicksellapp.util.formatPrice
import com.example.quicksellapp.util.tools.prefs.PrefsManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val prefsManager: PrefsManager,
    private val productsDao: ProductsDao
): ViewModel() {

    fun getFormattedPrice(actualPrice: String): String {
        if(actualPrice.isEmpty()) return ""
        val currency = QuickSellCurrency.fromLanguage(prefsManager.language)
        val finalPrice = currency.formatPrice(actualPrice.toLong())
        return "$finalPrice ${currency.currency}"
    }

   suspend fun saveProduct(product: Product) = productsDao.insert(product)
}