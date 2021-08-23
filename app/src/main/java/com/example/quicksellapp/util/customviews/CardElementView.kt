package com.example.quicksellapp.util.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.example.quicksellapp.R
import com.example.quicksellapp.databinding.CardElementBinding
import com.google.android.material.card.MaterialCardView

class CardElementView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : MaterialCardView(context, attrs, defStyleAttr) {

    private var binding : CardElementBinding =
        CardElementBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        attrs?.let { setupAttrs(it) }
    }

    private fun setupAttrs(attrs: AttributeSet) {
        val typedArray = context.theme.obtainStyledAttributes(
            attrs, R.styleable.CardElementView,
            0, 0
        )
        val img = typedArray.getDrawable(R.styleable.CardElementView_imgSrc)
        val title = typedArray.getString(R.styleable.CardElementView_cardTitle)

        with(binding) {
            cardTextTv.text = title
            img?.let {
                cardImageIv.setImageDrawable(it)
            }
            requestLayout()
        }
    }
}