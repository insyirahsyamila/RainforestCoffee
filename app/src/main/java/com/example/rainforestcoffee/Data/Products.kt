package com.example.rainforestcoffee.Data

data class Products (
    val id: String,
    val name: String,
    val category: String,
    val price: Float,
    val offerPercentage: Float? = null,
    val description: String? = null,
    val sizes: List<String>? = null,
    val images: List<String>
){
    constructor(): this("0", "", "", 0f, images = emptyList())
}