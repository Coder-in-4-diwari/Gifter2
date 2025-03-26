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
import com.example.gifter.model.GiftItemItem

class GiftItemAdapter(
    private val giftitemList: List<GiftItemItem>,
    private val onCartUpdate: (GiftItemItem, Int) -> Unit // Callback to update cart
) : RecyclerView.Adapter<GiftItemAdapter.GiftItemViewHolder>() {

    inner class GiftItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val giftitemImage: ImageView = itemView.findViewById(R.id.gift_itemImage)
        val giftitemName: TextView = itemView.findViewById(R.id.gift_itemName)
        val giftitemPrice: TextView = itemView.findViewById(R.id.gift_itemPrice)
        val btnAddToCart: Button = itemView.findViewById(R.id.btnAddToCart)
        val quantityLayout: View = itemView.findViewById(R.id.quantityLayout)
        val btnMinus: ImageButton = itemView.findViewById(R.id.btnMinus)
        val btnPlus: ImageButton = itemView.findViewById(R.id.btnPlus)
        val tvQuantity: TextView = itemView.findViewById(R.id.tvQuantity)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiftItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_gift_item, parent, false)
        return GiftItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: GiftItemViewHolder, position: Int) {
        val giftitem = giftitemList[position] // Changed from 'flower' to 'giftitem'

        holder.giftitemImage.setImageResource(giftitem.imageResId)
        holder.giftitemName.text = giftitem.name
        holder.giftitemPrice.text = "₹${giftitem.price}"

        // Manage visibility of Add to Cart and Quantity Layout
        if (giftitem.quantity > 0) {
            holder.btnAddToCart.visibility = View.GONE
            holder.quantityLayout.visibility = View.VISIBLE
            holder.tvQuantity.text = giftitem.quantity.toString()
        } else {
            holder.btnAddToCart.visibility = View.VISIBLE
            holder.quantityLayout.visibility = View.GONE
        }

        // Add to Cart Button Click
        holder.btnAddToCart.setOnClickListener {
            giftitem.quantity = 1
            notifyItemChanged(position)
            onCartUpdate(giftitem, giftitem.quantity) // Notify cart update
        }

        // Plus Button Click
        holder.btnPlus.setOnClickListener {
            giftitem.quantity++
            holder.tvQuantity.text = giftitem.quantity.toString()
            onCartUpdate(giftitem, giftitem.quantity) // Notify cart update
        }

        // Minus Button Click
        holder.btnMinus.setOnClickListener {
            if (giftitem.quantity > 1) {
                giftitem.quantity--
            } else {
                giftitem.quantity = 0
            }
            notifyItemChanged(position)
            onCartUpdate(giftitem, giftitem.quantity) // Notify cart update
        }
    }

    override fun getItemCount(): Int = giftitemList.size
}
