package com.example.rainforestcoffee.Login.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.rainforestcoffee.R
import com.example.rainforestcoffee.databinding.FragmentAccountOptionsBinding
import com.example.rainforestcoffee.databinding.FragmentIntroductionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountOptionsFragment : Fragment() {
    private lateinit var binding: FragmentAccountOptionsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAccountOptionsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.loginBtn.setOnClickListener{
            findNavController().navigate(R.id.action_accountOptionsFragment_to_loginFragment)
        }

        binding.registerBtn.setOnClickListener{
            findNavController().navigate(R.id.action_accountOptionsFragment_to_registerFragment2)
        }
    }
}
