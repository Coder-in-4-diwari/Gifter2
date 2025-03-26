package com.example.gifter.model  // Ensure this matches your package structure

data class FlowerItem(
    val name: String,
    val price: Int,
    val imageResId: Int,
    var quantity: Int = 0
)
