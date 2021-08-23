package com.example.quicksellapp.ui.home

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.quicksellapp.R
import com.example.quicksellapp.databinding.FragmentHomeBinding
import com.example.quicksellapp.util.tools.extensions.restartApp
import com.example.quicksellapp.model.LanguageType
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    private val mainViewModel: HomeViewModel by viewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)

        with(binding) {
            homeQuickSelBtn.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionNavHomeToQuickSell())
            }
            homeProductsBtn.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionNavHomeToProducts())
            }
            homeCloseBtn.setOnClickListener {
                requireActivity().finish()
            }
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_languages, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_language_en -> {
                mainViewModel.setupLanguage(LanguageType.Eng)
                restartApp()
                true
            }
            R.id.action_language_ro -> {
                mainViewModel.setupLanguage(LanguageType.Ro)
                restartApp()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}