package com.example.gifter

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gifter.model.FlowerItem
import com.example.gifter.adapter.FlowerAdapter

class FlowersActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var flowersAdapter: FlowerAdapter
    private val flowersList = mutableListOf<FlowerItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flowers)

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.flowersRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)

        // Populate the flowersList with default quantities
        flowersList.addAll(
            listOf(
                FlowerItem("Blue Orchids", 85, R.drawable.blue_orchids),
                FlowerItem("Rose", 50, R.drawable.rose),
                FlowerItem("Lily", 30, R.drawable.lily),
                FlowerItem("Pink Orchids", 85, R.drawable.pink_orchids),
                FlowerItem("Tulip", 35, R.drawable.tulip),
                FlowerItem("Sunflower", 60, R.drawable.sunflower)
            )
        )

        // Initialize Adapter with Cart Update Callback
        flowersAdapter = FlowerAdapter(flowersList) { updatedFlower, quantity ->
            handleCartUpdate(updatedFlower, quantity)
        }

        recyclerView.adapter = flowersAdapter
    }

    // Function to handle cart updates
    private fun handleCartUpdate(flower: FlowerItem, quantity: Int) {
        // Here you can update a cart database, show a Toast, or update UI
        Toast.makeText(this, "${flower.name} updated to $quantity in cart", Toast.LENGTH_SHORT).show()
    }
}
