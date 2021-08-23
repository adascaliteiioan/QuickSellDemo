package com.example.quicksellapp.ui.payment

import android.animation.Animator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.quicksellapp.R
import com.example.quicksellapp.databinding.FragmentPaymentBinding

class PaymentFragment : Fragment() {

    private var _binding: FragmentPaymentBinding? = null
    val binding get() = _binding!!

    private val args: PaymentFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPaymentBinding.inflate(layoutInflater, container, false)

        with(binding) {
            priceTv.text = args.price

            cancelBtn.setOnClickListener {
                setPaymentCancel()
            }
            cardPayBtn.setOnClickListener {
                setPaymentSuccess()
            }

            cashPayBtn.setOnClickListener {
                setPaymentSuccess()
            }
        }

        return binding.root
    }


    private fun setPaymentSuccess() {
        with(binding) {
            paymentSuccessAnim.isVisible = true
            container.isVisible = false
            paymentSuccessAnim.addAnimatorListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(p0: Animator?) = Unit

                override fun onAnimationCancel(p0: Animator?) = Unit

                override fun onAnimationRepeat(p0: Animator?) = Unit

                override fun onAnimationEnd(p0: Animator?) {
                    finishProcess()
                }

            })
            paymentSuccessAnim.playAnimation()
        }
    }

    private fun setPaymentCancel() {
        with(binding) {
            paymentCancelAnim.isVisible = true
            container.isVisible = false
            paymentCancelAnim.addAnimatorListener(object : Animator.AnimatorListener {
                override fun onAnimationStart(p0: Animator?) = Unit

                override fun onAnimationCancel(p0: Animator?) = Unit

                override fun onAnimationRepeat(p0: Animator?) = Unit

                override fun onAnimationEnd(p0: Animator?) {
                    finishProcess()
                }

            })
            paymentCancelAnim.playAnimation()
        }
    }


    private fun finishProcess() {
        findNavController().popBackStack(R.id.nav_home, false)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}