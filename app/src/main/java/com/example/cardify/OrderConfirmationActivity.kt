package com.example.cardify

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class OrderConfirmationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirmation)

        val details = """
            Vendor: ${intent.getStringExtra("vendor_name")}
            Price: ${intent.getStringExtra("price")}
            Delivery Time: ${intent.getStringExtra("delivery_time")}
            
            We've notified the vendor. 
            You'll receive a draft within 24 hours.
        """.trimIndent()

        findViewById<TextView>(R.id.tvConfirmationDetails).text = details

        findViewById<Button>(R.id.btnChat).setOnClickListener {
            startActivity(Intent(this, ChatActivity::class.java))
        }
    }
}