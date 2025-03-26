package com.example.gifter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gifter.R
import com.example.gifter.model.BouquetItem

class BouquetAdapter(
    private val bouquetList: List<BouquetItem>,
    private val onCartUpdate: (BouquetItem, Int) -> Unit // Callback to update cart
) : RecyclerView.Adapter<BouquetAdapter.BouquetViewHolder>() {

    inner class BouquetViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bouquetImage: ImageView = itemView.findViewById(R.id.bouquetImage)
        val bouquetName: TextView = itemView.findViewById(R.id.bouquetName)
        val bouquetPrice: TextView = itemView.findViewById(R.id.bouquetPrice)
        val btnAddToCart: Button = itemView.findViewById(R.id.btnAddToCart)
        val quantityLayout: View = itemView.findViewById(R.id.quantityLayout)
        val btnMinus: ImageButton = itemView.findViewById(R.id.btnMinus)
        val btnPlus: ImageButton = itemView.findViewById(R.id.btnPlus)
        val tvQuantity: TextView = itemView.findViewById(R.id.tvQuantity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BouquetViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_bouquet, parent, false)
        return BouquetViewHolder(view)
    }

    override fun onBindViewHolder(holder: BouquetViewHolder, position: Int) {
        val bouquet = bouquetList[position] // Changed from 'flower' to 'bouquet'

        holder.bouquetImage.setImageResource(bouquet.imageResId)
        holder.bouquetName.text = bouquet.name
        holder.bouquetPrice.text = "₹${bouquet.price}"

        // Manage visibility of Add to Cart and Quantity Layout
        if (bouquet.quantity > 0) {
            holder.btnAddToCart.visibility = View.GONE
            holder.quantityLayout.visibility = View.VISIBLE
            holder.tvQuantity.text = bouquet.quantity.toString()
        } else {
            holder.btnAddToCart.visibility = View.VISIBLE
            holder.quantityLayout.visibility = View.GONE
        }

        // Add to Cart Button Click
        holder.btnAddToCart.setOnClickListener {
            bouquet.quantity = 1
            notifyItemChanged(position)
            onCartUpdate(bouquet, bouquet.quantity) // Notify cart update
        }

        // Plus Button Click
        holder.btnPlus.setOnClickListener {
            bouquet.quantity++
            holder.tvQuantity.text = bouquet.quantity.toString()
            onCartUpdate(bouquet, bouquet.quantity) // Notify cart update
        }

        // Minus Button Click
        holder.btnMinus.setOnClickListener {
            if (bouquet.quantity > 1) {
                bouquet.quantity--
            } else {
                bouquet.quantity = 0
            }
            notifyItemChanged(position)
            onCartUpdate(bouquet, bouquet.quantity) // Notify cart update
        }
    }

    override fun getItemCount(): Int = bouquetList.size
}
