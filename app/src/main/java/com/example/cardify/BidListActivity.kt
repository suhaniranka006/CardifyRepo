package com.example.cardify

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.test.isSelected
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BidsListActivity : AppCompatActivity() {

    private lateinit var adapter: BidAdapter
    private val bids = mutableListOf(
        Bid("Royal Designs", "₹90", "7 days", "Peacock-themed with gold foil"),
        Bid("Minimal Art Co.", "₹80", "5 days", "Clean modern design"),
        Bid("Traditional Cards", "₹70", "10 days", "Hand-painted designs")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bid_list)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        findViewById<TextView>(R.id.tvRequirementDetails).text = """
            Name: ${intent.getStringExtra("name")}
            Deadline: ${intent.getStringExtra("deadline")}
            Budget: ₹${intent.getStringExtra("budget")}
            ${intent.getStringExtra("requirements")}
        """.trimIndent()

        setupBidsList()
        setupSelectButton()
    }

    private fun setupBidsList() {
        adapter = BidAdapter(this, bids) { position ->
            bids.forEachIndexed { index, bid ->
                bid.isSelected = index == position
            }
            adapter.notifyDataSetChanged()
        }
        findViewById<ListView>(R.id.lvBids).adapter = adapter
    }

    private fun setupSelectButton() {
        findViewById<Button>(R.id.btnSelectBid).setOnClickListener {
            bids.find { it.isSelected }?.let { selectedBid ->
                showConfirmationDialog(selectedBid)
            } ?: run {
                Toast.makeText(this, "Please select a bid first", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showConfirmationDialog(bid: Bid) {
        AlertDialog.Builder(this)
            .setTitle("Confirm Selection")
            .setMessage("""
                Confirm ${bid.vendorName}'s proposal?
                Price: ${bid.price}
                Delivery: ${bid.deliveryTime}
            """.trimIndent())
            .setPositiveButton("Confirm") { _, _ ->
                showSuccessScreen(bid)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showSuccessScreen(bid: Bid) {
        startActivity(Intent(this, OrderConfirmationActivity::class.java).apply {
            putExtra("vendor_name", bid.vendorName)
            putExtra("price", bid.price)
            putExtra("delivery_time", bid.deliveryTime)
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}