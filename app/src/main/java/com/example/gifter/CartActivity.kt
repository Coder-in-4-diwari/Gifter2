package com.example.gifter

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gifter.databinding.ActivityCartBinding

class CartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCartBinding
    private lateinit var cartAdapter: CartAdapter
    private val cartItems: ArrayList<CartItem> = arrayListOf() // Explicit type defined

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true) // Show back button
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_back_arrow) // Set custom back icon
        supportActionBar?.title = "Checkout" // Set title inside toolbar

        binding.toolbar.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Now calling methods after setting up the toolbar
        setupRecyclerView()
        setupListeners()
    }

    private fun setupRecyclerView() {
        cartAdapter = CartAdapter(cartItems)
        binding.cartRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.cartRecyclerView.adapter = cartAdapter
    }

    private fun setupListeners() {
        binding.checkoutButton.setOnClickListener {
            // Handle place order functionality
        }
    }
}
