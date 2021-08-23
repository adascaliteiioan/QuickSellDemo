package com.example.quicksellapp.ui.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quicksellapp.databinding.ItemProductsCategoryBinding
import com.example.quicksellapp.model.Product
import com.example.quicksellapp.model.ProductsCategory

class ProductsCategoryAdapter(
    private val onPlusClick: (Product) -> Unit,
    private val onMinusClick: (Product) -> Unit
) :
    ListAdapter<ProductsCategory, ProductsCategoryAdapter.ViewHolder>(ProductsCategoryDiffUtil()) {

    inner class ViewHolder(private val binding: ItemProductsCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private val adapter = ProductsAdapter(onPlusClick, onMinusClick)

        init {
            binding.productsListRv.adapter = adapter
        }

        fun bind(productsCategory: ProductsCategory) {
            binding.categoryNameTv.text = productsCategory.category
            adapter.submitList(productsCategory.products)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemProductsCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ProductsCategoryDiffUtil : DiffUtil.ItemCallback<ProductsCategory>() {
    override fun areItemsTheSame(oldItem: ProductsCategory, newItem: ProductsCategory) =
        oldItem == newItem

    override fun areContentsTheSame(oldItem: ProductsCategory, newItem: ProductsCategory) =
        oldItem.category == newItem.category &&
                oldItem.products == newItem.products

}