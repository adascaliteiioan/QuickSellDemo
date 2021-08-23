package com.example.quicksellapp.ui.products

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quicksellapp.databinding.ItemProductBinding
import com.example.quicksellapp.model.Product
import com.example.quicksellapp.util.tools.extensions.formatPrice

class ProductsAdapter(
    private val onPlusClick: (Product) -> Unit,
    private val onMinusClick: (Product) -> Unit
) : ListAdapter<Product, ProductsAdapter.ViewHolder>(ProductDiffUtil()) {

    inner class ViewHolder(private val binding: ItemProductBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var product: Product? = null

        fun bind(product: Product) {
            this.product = product
            with(binding) {
                nameTv.text = product.name
                priceTv.text = product.price.formatPrice(binding.root.context)
                quantityTv.text = "${product.quantity.toString()} pieces"
                quantityOrderedTv.text = product.quantityOrdered.toString()

                plusBtn.setOnClickListener {
                    product.quantityOrdered++
                    notifyItemChanged(currentList.indexOf(product))
                    onPlusClick(product)
                }
                minusBtn.setOnClickListener {
                    product.quantityOrdered--
                    notifyItemChanged(currentList.indexOf(product))
                    onMinusClick(product)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class ProductDiffUtil : DiffUtil.ItemCallback<Product>() {
    override fun areItemsTheSame(oldItem: Product, newItem: Product) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Product, newItem: Product) =
        oldItem.brand == newItem.brand &&
                oldItem.category == newItem.category &&
                oldItem.description == newItem.description &&
                oldItem.id == newItem.id &&
                oldItem.name == newItem.name &&
                oldItem.price == newItem.price &&
                oldItem.quantity == newItem.quantity

}