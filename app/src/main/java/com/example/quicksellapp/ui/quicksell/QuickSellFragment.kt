package com.example.quicksellapp.ui.quicksell

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.ItemTouchHelper.SimpleCallback
import androidx.recyclerview.widget.RecyclerView
import com.example.quicksellapp.R
import com.example.quicksellapp.databinding.FragmentQuickSellBinding
import com.example.quicksellapp.model.Product
import com.example.quicksellapp.util.Resource
import com.example.quicksellapp.util.customviews.AddImageView
import com.example.quicksellapp.util.customviews.ImageInfoView
import com.example.quicksellapp.util.tools.extensions.dp
import com.example.quicksellapp.util.tools.extensions.showDialog
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class QuickSellFragment : Fragment() {

    private var _binding: FragmentQuickSellBinding? = null
    private val binding get() = _binding!!

    private lateinit var categoriesAdapter: CategoriesAdapter
    private val viewModel: QuickSellViewModel by viewModels()

    private lateinit var addImageView: AddImageView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuickSellBinding.inflate(layoutInflater, container, false)

        addImageView = AddImageView(requireContext())
        addImageView.setListener {

        }

        categoriesAdapter = CategoriesAdapter { viewModel.selectedCategory = null }

        with(binding) {
            categoriesRv.adapter = categoriesAdapter
            categoriesRv.layoutManager =
                FlexboxLayoutManager(requireContext(), FlexDirection.ROW, FlexWrap.WRAP)
            categoriesItemTouchHelper.attachToRecyclerView(categoriesRv)

            viewLifecycleOwner.lifecycleScope.launchWhenStarted {
                viewModel.getCategories().collect { categRes ->
                    when (categRes) {
                        is Resource.Error -> {

                        }
                        is Resource.Loading -> {

                        }
                        is Resource.Success -> {
                            categoriesAdapter.submitList(categRes.data)
                        }
                    }
                }

                viewModel.photoList.collect { photos ->
//                    setupImageContainer(photos)
                }
            }

            keyboardView.setOkClickListener { amount ->
                if (amount == "0") {
                    showDialog(
                        getString(R.string.no_amount_title),
                        getString(R.string.no_amount_message)
                    )
                    return@setOkClickListener
                }

                if (viewModel.selectedCategory == null) {
                    showDialog(
                        getString(R.string.no_category_title),
                        getString(R.string.no_category_message)
                    )
                    return@setOkClickListener
                }

                findNavController().navigate(
                    QuickSellFragmentDirections.actionQuickSellFragmentToProductDetails(
                        viewModel.selectedCategory!!.name, amount
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

    private val categoriesItemTouchHelper =
        ItemTouchHelper(object : SimpleCallback(0, ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean = true

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (viewHolder is CategoriesAdapter.CategoryViewHolder) {
                    if (direction == ItemTouchHelper.RIGHT) {
                        viewHolder.onSwipeUpdate()

                        viewModel.selectedCategory =
                            categoriesAdapter.currentList.first { it.isSelected }
                    }
                }
            }
        })

//    private fun setupImageContainer(photos: List<String>) {
//        val childCount = binding.imageAddContainer.childCount
//        binding.imageAddContainer.removeAllViews()
//
//        if (photos.isEmpty()) {
//            binding.imageAddContainer.addView(addImageView)
//        } else {
//
//            val maxViewNo =
//                (binding.root.width - ((childCount + 1) * IMAGE_INFO_MARGIN)) / addImageView.width
//
//            val viewsToShow = photos.takeLast(maxViewNo - 1)
//            viewsToShow.forEach { photoPath ->
//                binding.imageAddContainer.addView(createImageInfo(photoPath))
//            }
//            binding.imageAddContainer.addView(addImageView)
//        }
//    }

    private fun createImageInfo(photo: String): ImageInfoView {
        val imageInfoView = ImageInfoView(requireContext())
        imageInfoView.setImageUri(Uri.parse(photo))
        val params = addImageView.layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(0, 0, IMAGE_INFO_MARGIN, 0)
        imageInfoView.layoutParams = params
        imageInfoView.setOnCancelClickListener {
            viewModel.removePhoto(photo)
        }
        return imageInfoView
    }

    companion object {
        private val IMAGE_INFO_MARGIN = 4.dp
        private const val FILENAME_FORMAT = "yyyy-MM-dd-HH-mm-ss-SSS"
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.CAMERA)
    }
}