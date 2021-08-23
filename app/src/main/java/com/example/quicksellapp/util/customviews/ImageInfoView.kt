package com.example.quicksellapp.util.customviews

import android.content.Context
import android.net.Uri
import android.util.AttributeSet
import android.view.LayoutInflater
import com.example.quicksellapp.databinding.CustomImageInfoBinding
import com.google.android.material.card.MaterialCardView

class ImageInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    private val binding: CustomImageInfoBinding =
        CustomImageInfoBinding.inflate(LayoutInflater.from(context), this, true)

    fun setOnCancelClickListener(listener: () -> Unit) {
        binding.cancelImg.setOnClickListener { listener() }
    }

    fun setImageUri(imgUri: Uri) {
        binding.selectedImg.setImageURI(imgUri)
    }

    fun setImageRes(imgRes: Int) {
        binding.selectedImg.setImageResource(imgRes)
    }
}