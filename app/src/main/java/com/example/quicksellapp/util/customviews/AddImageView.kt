package com.example.quicksellapp.util.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.example.quicksellapp.databinding.CustomAddImageBinding
import com.google.android.material.card.MaterialCardView

class AddImageView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    private val binding: CustomAddImageBinding =
        CustomAddImageBinding.inflate(LayoutInflater.from(context), this, true)

    fun setListener(listener: ()->Unit) {
        binding.rootAddImage.setOnClickListener { listener() }
    }
}