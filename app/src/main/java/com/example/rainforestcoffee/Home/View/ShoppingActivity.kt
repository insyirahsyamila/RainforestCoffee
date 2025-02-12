package com.example.rainforestcoffee.Home.View

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.rainforestcoffee.R
import com.example.rainforestcoffee.databinding.ActivityShoppingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.hostFragment) as? NavHostFragment
        if (navHostFragment != null) {
            val navController = navHostFragment.navController
            binding.bottomNav.setupWithNavController(navController)
        } else {
            throw IllegalStateException("NavHostFragment is not found in ShoppingActivity")
        }
    }
}