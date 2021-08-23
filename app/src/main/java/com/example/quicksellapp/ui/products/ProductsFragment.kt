package com.example.quicksellapp.ui.products

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.quicksellapp.databinding.FragmentProductsBinding
import com.example.quicksellapp.model.Product
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class ProductsFragment : Fragment() {

    private var _binding: FragmentProductsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductsViewModel by viewModels()

    private lateinit var productCategoryAdapter: ProductsCategoryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductsBinding.inflate(layoutInflater, container, false)

        productCategoryAdapter = ProductsCategoryAdapter(
            onPlusClick = {
                viewModel.updateProductOrder(it)
            },
            onMinusClick = {
                viewModel.updateProductOrder(it)
            })
        binding.productsCategoryList.adapter = productCategoryAdapter

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.getAllProducts().collect { products ->
                if(products.isEmpty()) {
                    binding.emptyStateIv.isVisible = true
                    binding.payBtn.isVisible = false
                    binding.productsCategoryList.isVisible = false
                } else {
                    binding.emptyStateIv.isVisible = false
                    binding.payBtn.isVisible = true
                    binding.productsCategoryList.isVisible = true
                    productCategoryAdapter.submitList(products)
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.currentAmount.collect {
                binding.payBtn.text = "Pay $it"
            }
        }

        binding.payBtn.setOnClickListener {
            val currentPrice = viewModel.currentAmount.value
            if (viewModel.canPay()) {
                findNavController().navigate(
                    ProductsFragmentDirections.actionProductsFragmentToPaymentFragment(
                        currentPrice
                    )
                )
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}