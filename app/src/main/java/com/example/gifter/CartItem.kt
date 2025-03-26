package com.example.gifter

data class CartItem(
    val imageRes: Int,  // Resource ID of the product image
    val name: String,    // Product name
    val price: Double,   // Price of the item
    val quantity: Int    // Quantity of the item
)
