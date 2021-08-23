package com.example.quicksellapp.ui.products.details

import android.animation.Animator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.quicksellapp.R
import com.example.quicksellapp.databinding.FragmentProductDetailsBinding
import com.example.quicksellapp.model.Product
import com.example.quicksellapp.util.tools.extensions.showDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailsFragment : Fragment() {

    private var _binding: FragmentProductDetailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ProductDetailsViewModel by viewModels()

    private val args: ProductDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductDetailsBinding.inflate(layoutInflater, container, false)

        with(binding) {
            categoryTv.text = args.productCategory
            priceTv.text = viewModel.getFormattedPrice(args.productPrice.orEmpty())
            saveBtn.setOnClickListener {
                if (validate()) {
                    val product = Product(
                        name = nameTv.text.toString(),
                        brand = brandTv.text.toString(),
                        category = categoryTv.text.toString(),
                        quantity = quantityTv.text.toString().toInt(),
                        price = args.productPrice?.toLong() ?: 0,
                        description = descriptionTv.text.toString()
                    )
                    viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                        viewModel.saveProduct(product)
                    }
                    productContainer.isVisible = false
                    doneAnimation.isVisible = true
                    doneAnimation.playAnimation()
                    doneAnimation.addAnimatorListener(object : Animator.AnimatorListener {
                        override fun onAnimationStart(p0: Animator?) = Unit

                        override fun onAnimationCancel(p0: Animator?) = Unit

                        override fun onAnimationRepeat(p0: Animator?) = Unit

                        override fun onAnimationEnd(p0: Animator?) {
                            findNavController().popBackStack(R.id.nav_home, false)
                        }
                    })
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun validate(): Boolean {
        with(binding) {
            if (nameTv.text.isEmpty()) {
                showDialog(
                    getString(R.string.product_details_name_hint),
                    getString(R.string.product_details_name_error)
                )
                return false
            }

            if (quantityTv.text.isEmpty()) {
                showDialog(
                    getString(R.string.product_details_quantity_hint),
                    getString(R.string.product_details_quantity_error)
                )
                return false
            }

            if (brandTv.text.isEmpty()) {
                showDialog(
                    getString(R.string.product_details_brand_hint),
                    getString(R.string.product_details_brand_error)
                )
                return false
            }

            if (descriptionTv.text.isEmpty()) {
                showDialog(
                    getString(R.string.product_details_description_hint),
                    getString(R.string.product_details_description_error)
                )
                return false
            }
        }
        return true
    }
}