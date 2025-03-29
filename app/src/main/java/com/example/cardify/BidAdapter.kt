package com.example.cardify

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


class BidAdapter(
    private val context: Context,
    private val bids: List<Bid>,
    private val onItemClick: (Int) -> Unit
) : BaseAdapter() {

    override fun getCount(): Int = bids.size
    override fun getItem(position: Int): Bid = bids[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.bid_item, parent, false)

        val bid = getItem(position)

        view.findViewById<TextView>(R.id.tvVendorName).text = bid.vendorName
        view.findViewById<TextView>(R.id.tvPrice).text = "Price: ${bid.price}"
        view.findViewById<TextView>(R.id.tvDeliveryTime).text = "Delivery: ${bid.deliveryTime}"
        view.findViewById<TextView>(R.id.tvProposal).text = bid.proposal

        // Highlight selected item
        view.setBackgroundColor(
            if (bid.isSelected) Color.LTGRAY
            else Color.TRANSPARENT
        )

        view.setOnClickListener { onItemClick(position) }

        return view
    }
}