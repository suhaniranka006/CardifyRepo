package com.example.cardify

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class BidSubmissionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bid_submision)

        // Client requirement display
        val requirement = intent.getStringExtra("requirement")
        findViewById<TextView>(R.id.tvClientRequirement).text = requirement

        // Submit bid button
        findViewById<Button>(R.id.btnSubmitBid).setOnClickListener {
            submitBid()
        }
    }

    private fun submitBid() {
        val bidAmount = findViewById<EditText>(R.id.etBidAmount).text.toString()
        val deliveryTime = findViewById<EditText>(R.id.etDeliveryTime).text.toString()
        val proposal = findViewById<EditText>(R.id.etProposal).text.toString()

        when {
            bidAmount.isEmpty() -> showError("Please enter your price")
            deliveryTime.isEmpty() -> showError("Please enter delivery time")
            proposal.isEmpty() -> showError("Please write your proposal")
            else -> {
                // Create bid object
                val bid = Bid(
                    vendorName = "Current Vendor", // Replace with actual vendor name
                    price = "₹$bidAmount",
                    deliveryTime = "$deliveryTime days",
                    proposal = proposal,

                )

                // TODO: Save to database (Firebase/API)
                showSuccessAndExit(bid)
            }
        }
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showSuccessAndExit(bid: Bid) {
        AlertDialog.Builder(this)
            .setTitle("Bid Submitted!")
            .setMessage("""
                Your bid has been submitted:
                
                Price: ${bid.price}
                Delivery: ${bid.deliveryTime}
            """.trimIndent())
            .setPositiveButton("OK") { _, _ ->
                finish()
            }
            .show()
    }
}