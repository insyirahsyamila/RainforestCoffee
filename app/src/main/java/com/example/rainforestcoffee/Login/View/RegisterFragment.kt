package com.example.rainforestcoffee.Login.View

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.rainforestcoffee.Data.User
import com.example.rainforestcoffee.Login.ViewModel.RegisterViewModel
import com.example.rainforestcoffee.R
import com.example.rainforestcoffee.databinding.FragmentRegisterBinding
import com.example.rainforestcoffee.util.Resource
import dagger.hilt.android.AndroidEntryPoint

private val TAG = "RegisterFragment"

@AndroidEntryPoint
class RegisterFragment : Fragment() {

    private lateinit var binding: FragmentRegisterBinding
    private val viewModel by viewModels<RegisterViewModel>()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentRegisterBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            registerBtn.setOnClickListener{
                val user = User(
                    firstName.text.toString().trim(),
                    lastName.text.toString().trim(),
                    gmailEditTv.text.toString().trim()
                )
                val password = passEditTv.text.toString()
                viewModel.createAccountWithEmailAndPassword(user, password)
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.register.collect {
                when (it){
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        Log.d("RegisterSuccess", it.data.toString())
                        Toast.makeText(requireContext(), getString(R.string.account_created),Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Error -> {
                        Log.e(TAG, it.message.toString())
                        Toast.makeText(requireContext(), getString(R.string.cannot_create_account),Toast.LENGTH_SHORT).show()
                    }
                    else -> Unit
                }
            }
        }
    }
}
