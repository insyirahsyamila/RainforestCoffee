package com.example.rainforestcoffee.Home.View

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rainforestcoffee.Home.Adapter.HomeViewPagerAdapter
import com.example.rainforestcoffee.R
import com.example.rainforestcoffee.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val categoriesFragments = arrayListOf<Fragment>(
            MainCategoryFragment(), // Position 0
            CoffeeFragment(),       // Position 1
            NonCoffeeFragment(),    // Position 2
            PastryFragment(),       // Position 3
            BlendedFragment()       // Position 4
        )

        binding.viewPager.isUserInputEnabled = false

        val viewPager2Adapter = HomeViewPagerAdapter(categoriesFragments, childFragmentManager, lifecycle)
        binding.viewPager.adapter = viewPager2Adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Main"
                1 -> tab.text = "Coffee"
                2 -> tab.text = "Non-Coffee"
                3 -> tab.text = "Pastry"
                4 -> tab.text = "Blended"
            }
        }.attach()
    }
}