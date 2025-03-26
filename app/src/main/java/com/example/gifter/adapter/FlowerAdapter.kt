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
import com.example.gifter.model.FlowerItem

class FlowerAdapter(
    private val flowerList: List<FlowerItem>,
    private val onCartUpdate: (FlowerItem, Int) -> Unit // Callback to update cart
) : RecyclerView.Adapter<FlowerAdapter.FlowerViewHolder>() {

    inner class FlowerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val flowerImage: ImageView = itemView.findViewById(R.id.flowerImage)
        val flowerName: TextView = itemView.findViewById(R.id.flowerName)
        val flowerPrice: TextView = itemView.findViewById(R.id.flowerPrice)
        val btnAddToCart: Button = itemView.findViewById(R.id.btnAddToCart)
        val quantityLayout: View = itemView.findViewById(R.id.quantityLayout)
        val btnMinus: ImageButton = itemView.findViewById(R.id.btnMinus)
        val btnPlus: ImageButton = itemView.findViewById(R.id.btnPlus)
        val tvQuantity: TextView = itemView.findViewById(R.id.tvQuantity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_flower, parent, false)
        return FlowerViewHolder(view)
    }

    override fun onBindViewHolder(holder: FlowerViewHolder, position: Int) {
        val flower = flowerList[position]

        holder.flowerImage.setImageResource(flower.imageResId)
        holder.flowerName.text = flower.name
        holder.flowerPrice.text = "₹${flower.price}"

        // Manage visibility of Add to Cart and Quantity Layout
        if (flower.quantity > 0) {
            holder.btnAddToCart.visibility = View.GONE
            holder.quantityLayout.visibility = View.VISIBLE
            holder.tvQuantity.text = flower.quantity.toString()
        } else {
            holder.btnAddToCart.visibility = View.VISIBLE
            holder.quantityLayout.visibility = View.GONE
        }

        // Add to Cart Button Click
        holder.btnAddToCart.setOnClickListener {
            flower.quantity = 1
            notifyItemChanged(position)
            onCartUpdate(flower, flower.quantity) // Notify cart update
        }

        // Plus Button Click
        holder.btnPlus.setOnClickListener {
            flower.quantity++
            holder.tvQuantity.text = flower.quantity.toString()
            onCartUpdate(flower, flower.quantity) // Notify cart update
        }

        // Minus Button Click
        holder.btnMinus.setOnClickListener {
            if (flower.quantity > 1) {
                flower.quantity--
            } else {
                flower.quantity = 0
            }
            notifyItemChanged(position)
            onCartUpdate(flower, flower.quantity) // Notify cart update
        }
    }

    override fun getItemCount(): Int = flowerList.size
}
