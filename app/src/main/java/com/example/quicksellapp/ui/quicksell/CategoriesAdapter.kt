package com.example.quicksellapp.ui.quicksell

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quicksellapp.databinding.ItemCategoryBinding
import com.example.quicksellapp.model.Category

class CategoriesAdapter(private val onCancelClick: () -> Unit) :
    ListAdapter<Category, CategoriesAdapter.CategoryViewHolder>(CategoryDiffUtil()) {

    inner class CategoryViewHolder(private val binding: ItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var category: Category? = null

        init {
            binding.categoryCancelImg.setOnClickListener {
                onCancelClick()
                updateCurrentCategory()
            }
        }

        fun bind(category: Category) {
            this.category = category
            with(binding) {
                categoryNameTv.text = category.name
                categoryCancelImg.isVisible = category.isSelected
            }
        }

        fun onSwipeUpdate() {
            category?.let {
                if (it.isSelected.not()) {
                    currentList.forEach { it.isSelected = false }
                    currentList[getCurrentItemPosition()].isSelected = true
                    notifyDataSetChanged()
                } else {
                    notifyItemChanged(getCurrentItemPosition())
                }
            }
        }

        private fun updateCurrentCategory() {
            category?.let {
                it.isSelected = !it.isSelected
                notifyItemChanged(getCurrentItemPosition())
            }
        }

        private fun getCurrentItemPosition(): Int =
            category?.let { currentList.indexOf(it) } ?: 0

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class CategoryDiffUtil : DiffUtil.ItemCallback<Category>() {
    override fun areItemsTheSame(oldItem: Category, newItem: Category): Boolean = oldItem == newItem


    override fun areContentsTheSame(oldItem: Category, newItem: Category): Boolean =
        oldItem.isSelected == newItem.isSelected
                && oldItem.name == newItem.name

}