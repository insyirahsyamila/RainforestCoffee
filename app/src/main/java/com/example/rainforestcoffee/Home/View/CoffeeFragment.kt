package com.example.rainforestcoffee.Home.View

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.rainforestcoffee.R
import com.example.rainforestcoffee.databinding.FragmentBaseCategoryBinding
import com.example.rainforestcoffee.databinding.FragmentCoffeeBinding

class CoffeeFragment : BaseCategoryFragment() {
    private lateinit var binding: FragmentCoffeeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCoffeeBinding.inflate(inflater)
        return binding.root
    }
}