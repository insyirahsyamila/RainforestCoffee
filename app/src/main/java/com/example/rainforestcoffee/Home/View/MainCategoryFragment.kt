package com.example.rainforestcoffee.Home.View

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rainforestcoffee.Home.Adapter.BestProductsAdapter
import com.example.rainforestcoffee.Home.Adapter.PromoDealsAdapter
import com.example.rainforestcoffee.Home.Adapter.SpecialProductAdapter
import com.example.rainforestcoffee.Home.ViewModel.MainCategoryViewModel
import com.example.rainforestcoffee.databinding.FragmentMainCategoryBinding
import com.example.rainforestcoffee.util.ItemSpacing
import com.example.rainforestcoffee.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

private val TAG = "MainCategoryFragment"
@AndroidEntryPoint

class MainCategoryFragment: Fragment() {
    private lateinit var binding: FragmentMainCategoryBinding
    private lateinit var specialProductAdapter: SpecialProductAdapter
    private lateinit var bestProductsAdapter: BestProductsAdapter
    private lateinit var promoDealsAdapter: PromoDealsAdapter
    private val viewModel by viewModels<MainCategoryViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d("MainCategoryFragment", "onCreateView called")
        binding = FragmentMainCategoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSpecialProduct()
        setupPromoDeals()
        setupBestProducts()
        lifecycleScope.launchWhenStarted {
            viewModel.specialProducts.collectLatest {
                binding.specialPromoRv.isNestedScrollingEnabled = false
                when(it){
                    is Resource.Loading -> {
                        showLoading()
                    }
                    is Resource.Success -> {
                        specialProductAdapter.differ.submitList(it.data)
                        hideLoading()
                    }
                    is Resource.Error -> {
                        hideLoading()
                        Log.e(TAG, it.message.toString())
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.promoDeals.collectLatest {
                binding.promoDealsRv.isNestedScrollingEnabled = false
                when(it){
                    is Resource.Loading -> {
                        showLoading()
                    }
                    is Resource.Success -> {
                        promoDealsAdapter.differ.submitList(it.data)
                        hideLoading()
                    }
                    is Resource.Error -> {
                        hideLoading()
                        Log.e(TAG, it.message.toString())
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.bestProducts.collectLatest {
                binding.bestProductsRv.isNestedScrollingEnabled = false
                when(it){
                    is Resource.Loading -> {
                        showLoading()
                    }
                    is Resource.Success -> {
                        bestProductsAdapter.differ.submitList(it.data)
                        hideLoading()
                    }
                    is Resource.Error -> {
                        hideLoading()
                        Log.e(TAG, it.message.toString())
                        Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun setupBestProducts() {
        bestProductsAdapter = BestProductsAdapter()
        binding.bestProductsRv.apply {
            layoutManager = GridLayoutManager(requireContext(), 2, GridLayoutManager.VERTICAL, false)
            adapter = bestProductsAdapter
        }
    }

    private fun setupPromoDeals() {
        promoDealsAdapter = PromoDealsAdapter()
        binding.promoDealsRv.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = promoDealsAdapter
            addItemDecoration(ItemSpacing(18))
        }
    }

    private fun hideLoading() {
        binding.progressLoad.visibility = View.GONE
    }

    private fun showLoading() {
        binding.progressLoad.visibility = View.VISIBLE
    }

    private fun setupSpecialProduct() {
        specialProductAdapter = SpecialProductAdapter()
        binding.specialPromoRv.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = specialProductAdapter
        }
    }
}