package com.example.quicksellapp.util.customviews

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.quicksellapp.databinding.CustomKeyboardViewBinding
import java.lang.StringBuilder

class KeyboardView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: CustomKeyboardViewBinding =
        CustomKeyboardViewBinding.inflate(LayoutInflater.from(context), this, true)

    init {
        with(binding) {
            keyboard0.setOnClickListener {
                amountTv.text = getActualAmount(Keyboard.KEYBOARD_0)
            }
            keyboard1.setOnClickListener {
                amountTv.text = getActualAmount(Keyboard.KEYBOARD_1)
            }
            keyboard2.setOnClickListener {
                amountTv.text = getActualAmount(Keyboard.KEYBOARD_2)
            }
            keyboard3.setOnClickListener {
                amountTv.text = getActualAmount(Keyboard.KEYBOARD_3)
            }
            keyboard4.setOnClickListener {
                amountTv.text = getActualAmount(Keyboard.KEYBOARD_4)
            }
            keyboard5.setOnClickListener {
                amountTv.text = getActualAmount(Keyboard.KEYBOARD_5)
            }
            keyboard6.setOnClickListener {
                amountTv.text = getActualAmount(Keyboard.KEYBOARD_6)
            }
            keyboard7.setOnClickListener {
                amountTv.text = getActualAmount(Keyboard.KEYBOARD_7)
            }
            keyboard8.setOnClickListener {
                amountTv.text = getActualAmount(Keyboard.KEYBOARD_8)
            }
            keyboard9.setOnClickListener {
                amountTv.text = getActualAmount(Keyboard.KEYBOARD_9)
            }
            keyboardC.setOnClickListener {
                amountTv.text = getActualAmount(Keyboard.KEYBOARD_C)
            }
            keyboardX.setOnClickListener {
                amountTv.text = getActualAmount(Keyboard.KEYBOARD_X)
            }
            keyboardDoubleMultiplyTen.setOnClickListener {
                amountTv.text = getActualAmount(Keyboard.KEYBOARD_100)
            }
            keyboardTripleMultiplyTen.setOnClickListener {
                amountTv.text = getActualAmount(Keyboard.KEYBOARD_1000)
            }

        }
    }

    fun setOkClickListener(listener: (String) -> Unit) {
        binding.keyboardOk.setOnClickListener {
            listener(binding.amountTv.text.toString())
        }
    }

    private fun getActualAmount(key: Keyboard): String {
        var currentAmount = binding.amountTv.text.toString()

        if (currentAmount.length >= binding.amountTv.maxWidth)
            return currentAmount

        if (currentAmount == "0") {
            currentAmount = ""
        }
        val amount = StringBuilder()

        when (key) {
            Keyboard.KEYBOARD_0,
            Keyboard.KEYBOARD_1,
            Keyboard.KEYBOARD_2,
            Keyboard.KEYBOARD_3,
            Keyboard.KEYBOARD_4,
            Keyboard.KEYBOARD_5,
            Keyboard.KEYBOARD_6,
            Keyboard.KEYBOARD_7,
            Keyboard.KEYBOARD_8,
            Keyboard.KEYBOARD_9 -> {
                amount.append(currentAmount)
                    .append(key.value)
            }
            Keyboard.KEYBOARD_C -> {
                if (currentAmount.length > 1) {
                    amount.append(currentAmount.substring(0, currentAmount.length - 1))
                } else {
                    amount.append("0")
                }
            }
            Keyboard.KEYBOARD_X -> {
                amount.append("0")
            }
            Keyboard.KEYBOARD_OK -> TODO()
            Keyboard.KEYBOARD_100 -> {
                amount.append(currentAmount)
                    .append("00")
            }
            Keyboard.KEYBOARD_1000 -> {
                amount.append(currentAmount)
                    .append("000")
            }
        }
        return amount.toString()
    }
}

enum class Keyboard(val value: String) {
    KEYBOARD_0("0"),
    KEYBOARD_1("1"),
    KEYBOARD_2("2"),
    KEYBOARD_3("3"),
    KEYBOARD_4("4"),
    KEYBOARD_5("5"),
    KEYBOARD_6("6"),
    KEYBOARD_7("7"),
    KEYBOARD_8("8"),
    KEYBOARD_9("9"),
    KEYBOARD_C("C"),
    KEYBOARD_X("X"),
    KEYBOARD_OK("OK"),
    KEYBOARD_100("100"),
    KEYBOARD_1000("1000")
}