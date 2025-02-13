package com.example.rainforestcoffee.Home.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rainforestcoffee.Data.Products
import com.example.rainforestcoffee.util.Resource
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainCategoryViewModel @Inject constructor(
    private val firestore: FirebaseFirestore
): ViewModel() {

    private val _specialProducts = MutableStateFlow<Resource<List<Products>>>(Resource.Unspecified())
    val specialProducts: StateFlow<Resource<List<Products>>> = _specialProducts

    private val _promoDeals = MutableStateFlow<Resource<List<Products>>>(Resource.Unspecified())
    val promoDeals: StateFlow<Resource<List<Products>>> = _promoDeals

    private val _bestProducts = MutableStateFlow<Resource<List<Products>>>(Resource.Unspecified())
    val bestProducts: StateFlow<Resource<List<Products>>> = _bestProducts

    init {
        fetchSpecialProducts()
        fetchPromoDeals()
        fetchBestProducts()
    }

    fun fetchSpecialProducts(){
        viewModelScope.launch {
            _specialProducts.emit(Resource.Loading())
        }
        firestore.collection("Products")
            .whereEqualTo("category", "Coffee").get().addOnSuccessListener { result ->
                val specialProductsList = result.toObjects(Products::class.java)
               viewModelScope.launch {
                   _specialProducts.emit(Resource.Success(specialProductsList))
               }
        }.addOnFailureListener{
                viewModelScope.launch {
                    _specialProducts.emit(Resource.Error(it.message.toString()))
                }
        }
    }

    fun fetchPromoDeals(){
        viewModelScope.launch {
            _promoDeals.emit(Resource.Loading())
        }
        firestore.collection("Products").get()
            .addOnSuccessListener { result ->
                val promoDealsList = result.toObjects(Products::class.java)
                viewModelScope.launch {
                    _promoDeals.emit(Resource.Success(promoDealsList))
                }
            }.addOnFailureListener{
                viewModelScope.launch {
                    _promoDeals.emit(Resource.Error(it.message.toString()))
                }
            }
    }

    fun fetchBestProducts(){
        viewModelScope.launch {
            _bestProducts.emit(Resource.Loading())
        }
        firestore.collection("Products").get()
            .addOnSuccessListener { result ->
                val bestProductsList = result.toObjects(Products::class.java)
                viewModelScope.launch {
                    _bestProducts.emit(Resource.Success(bestProductsList))
                }
            }.addOnFailureListener{
                viewModelScope.launch {
                    _bestProducts.emit(Resource.Error(it.message.toString()))
                }
            }
    }
}