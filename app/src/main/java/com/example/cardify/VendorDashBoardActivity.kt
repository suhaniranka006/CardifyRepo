package com.example.cardify

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

// VendorDashboardActivity.kt
class VendorDashboardActivity : AppCompatActivity() {

    private val activeBids = listOf(
        "Wedding Card - Budget: ₹500 - Deadline: 15 Dec",
        "Birthday Card - Budget: ₹90 - Deadline: 10 Dec",
        "Anniversary Card - Budget: ₹200 - Deadline: 20 Dec"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vendor_dash_board)

        // Welcome message
        findViewById<TextView>(R.id.tvWelcome).text =
            "Welcome, ${intent.getStringExtra("vendor_name")}"

        // Setup bids list
        findViewById<ListView>(R.id.lvActiveBids).apply {
            adapter = ArrayAdapter(
                this@VendorDashboardActivity,
                android.R.layout.simple_list_item_1,
                activeBids
            )
            setOnItemClickListener { _, _, position, _ ->
                openBidSubmission(activeBids[position])
            }
        }
    }

    private fun openBidSubmission(requirement: String) {
        startActivity(Intent(this, BidSubmissionActivity::class.java).apply {
            putExtra("requirement", requirement)
        })
    }
}