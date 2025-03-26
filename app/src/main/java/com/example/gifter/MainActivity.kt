package com.example.gifter

import android.widget.LinearLayout
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gifter.model.FlowerItem
import com.example.gifter.adapter.FlowerAdapter
import com.example.gifter.model.BouquetItem
import com.example.gifter.adapter.BouquetAdapter
import com.example.gifter.model.GiftItemItem
import com.example.gifter.adapter.GiftItemAdapter
import com.example.gifter.databinding.ActivityCartBinding

class MainActivity : AppCompatActivity() {
    private lateinit var flowersRecyclerView: RecyclerView
    private lateinit var flowerAdapter: FlowerAdapter
    private lateinit var bouquetRecyclerView: RecyclerView
    private lateinit var bouquetAdapter: BouquetAdapter
    private lateinit var giftRecyclerView: RecyclerView
    private lateinit var giftAdapter: GiftItemAdapter

    // Store cart items separately
    private val flowerCart = mutableMapOf<FlowerItem, Int>()
    private val bouquetCart = mutableMapOf<BouquetItem, Int>()
    private val giftCart = mutableMapOf<GiftItemItem, Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Sample data for search suggestions
        val searchSuggestions = listOf(
            "Roses", "Tulips", "Lilies", "Daisies", "Sunflowers",
            "Birthday Gift", "Anniversary Gift", "Valentine's Bouquet"
        )

        // Find search input field
        val searchInput = findViewById<AutoCompleteTextView>(R.id.searchInput)
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, searchSuggestions)
        searchInput.setAdapter(adapter)
        searchInput.threshold = 3

        // Find category buttons
        findViewById<LinearLayout>(R.id.btnFavorites).setOnClickListener {
            startActivity(Intent(this, FavoritesActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.btnHistory).setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.btnOrders).setOnClickListener {
            startActivity(Intent(this, OrdersActivity::class.java))
        }

        // Setup Flowers RecyclerView
        flowersRecyclerView = findViewById(R.id.flowersRecyclerView)
        flowersRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val flowersList = listOf(
            FlowerItem("Blue Orchids", 85, R.drawable.blue_orchids),
            FlowerItem("Rose", 50, R.drawable.rose),
            FlowerItem("Lily", 30, R.drawable.lily),
            FlowerItem("Pink Orchids", 85, R.drawable.pink_orchids),
            FlowerItem("Tulip", 35, R.drawable.tulip),
            FlowerItem("Sunflower", 60, R.drawable.sunflower)
        )

        flowerAdapter = FlowerAdapter(flowersList) { flower, quantity ->
            updateFlowerCart(flower, quantity)
        }
        flowersRecyclerView.adapter = flowerAdapter

        // Setup Bouquets RecyclerView
        bouquetRecyclerView = findViewById(R.id.bouquetRecyclerView)
        bouquetRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val bouquetList = listOf(
            BouquetItem("Classic Roses", 120, R.drawable.rose_bouquet),
            BouquetItem("Tulip Mix", 150, R.drawable.bouquet),
            BouquetItem("Lily Charm", 135, R.drawable.lily_bouquet)
        )

        bouquetAdapter = BouquetAdapter(bouquetList) { bouquet, quantity ->
            updateBouquetCart(bouquet, quantity)
        }
        bouquetRecyclerView.adapter = bouquetAdapter

        // Setup Gift Items RecyclerView
        giftRecyclerView = findViewById(R.id.gift_itemRecyclerView)
        giftRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val giftList = listOf(
            GiftItemItem("Teddy Bear", 300, R.drawable.teddy_bear),
            GiftItemItem("Chocolate Box", 250, R.drawable.chocolate_box),
            GiftItemItem("Greeting Card", 50, R.drawable.greeting_card),
            GiftItemItem("Perfume Set", 750, R.drawable.perfume_set)
        )

        giftAdapter = GiftItemAdapter(giftList) { gift, quantity ->
            updateGiftCart(gift, quantity)
        }
        giftRecyclerView.adapter = giftAdapter

        findViewById<LinearLayout>(R.id.btnFlowers).setOnClickListener {
            startActivity(Intent(this, FlowersActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.btnBouquet).setOnClickListener {
            startActivity(Intent(this, BouquetActivity::class.java))
        }
        findViewById<LinearLayout>(R.id.btnGiftItems).setOnClickListener {
            startActivity(Intent(this, GiftItemActivity::class.java))
        }
    }

    // Function to update cart for Flowers
    private fun updateFlowerCart(flower: FlowerItem, quantity: Int) {
        if (quantity > 0) {
            flowerCart[flower] = quantity
        } else {
            flowerCart.remove(flower)
        }
        println("Flower Cart Updated: $flowerCart")
    }

    // Function to update cart for Bouquets
    private fun updateBouquetCart(bouquet: BouquetItem, quantity: Int) {
        if (quantity > 0) {
            bouquetCart[bouquet] = quantity
        } else {
            bouquetCart.remove(bouquet)
        }
        println("Bouquet Cart Updated: $bouquetCart")
    }

    // Function to update cart for Gift Items
    private fun updateGiftCart(gift: GiftItemItem, quantity: Int) {
        if (quantity > 0) {
            giftCart[gift] = quantity
        } else {
            giftCart.remove(gift)
        }
        println("Gift Cart Updated: $giftCart")
    }

    // Category Navigation Functions
    fun openFlowersCategory(view: android.view.View) {
        startActivity(Intent(this, FlowersActivity::class.java))
    }

    fun openBouquetsCategory(view: android.view.View) {
        startActivity(Intent(this, BouquetActivity::class.java))
    }

    fun openGiftItemCategory(view: android.view.View) {
        startActivity(Intent(this, GiftItemActivity::class.java))
    }

}

