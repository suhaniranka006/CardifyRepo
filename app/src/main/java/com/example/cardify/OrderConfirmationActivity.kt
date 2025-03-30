package com.example.cardify

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONObject
import kotlin.random.Random

//class OrderConfirmationActivity : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_order_confirmation)
//
//        val details = """
//            Vendor: ${intent.getStringExtra("vendor_name")}
//            Price: ${intent.getStringExtra("price")}
//            Delivery Time: ${intent.getStringExtra("delivery_time")}
//
//            We've notified the vendor.
//            You'll receive a draft within 24 hours.
//        """.trimIndent()
//
//        findViewById<TextView>(R.id.tvConfirmationDetails).text = details
//
//        findViewById<Button>(R.id.btnChat).setOnClickListener {
//            startActivity(Intent(this, ChatActivity::class.java))
//        }
//    }
//}


class OrderConfirmationActivity : AppCompatActivity(), PaymentResultListener {

    private lateinit var razorpayClient: Checkout
    private val paymentAmount = 500 // ₹5.00 (amount in paise)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirmation)

        // Initialize Razorpay
        razorpayClient = Checkout()
        razorpayClient.setKeyID("rzp_test_LIGxMwrT0dQWSv") // Replace with your actual key

        // Chat Button Click
        findViewById<Button>(R.id.btnChat).setOnClickListener {
            startActivity(Intent(this, ChatActivity::class.java))
        }

        // Payment Button Click
        findViewById<Button>(R.id.btnPayment).setOnClickListener {
            startPayment()
        }
    }

    private fun startPayment() {
        try {
            val options = JSONObject().apply {
                put("name", "Wedding Card Marketplace")
                put("description", "Payment for Order #${Random.nextInt(1000, 9999)}")
                put("currency", "INR")
                put("amount", paymentAmount)
                put("prefill", JSONObject().apply {
                    put("email", "customer@example.com")
                    put("contact", "9876543210")
                })
            }
            razorpayClient.open(this, options)
        } catch (e: Exception) {
            Toast.makeText(this, "Payment error: ${e.message}", Toast.LENGTH_SHORT).show()
        }
    }

    // Payment Success Callback
    override fun onPaymentSuccess(razorpayPaymentId: String?) {
        Toast.makeText(this, "Payment Successful! ID: $razorpayPaymentId", Toast.LENGTH_SHORT).show()
        // Update order status in your database here
    }

    // Payment Failure Callback
    override fun onPaymentError(errorCode: Int, message: String?) {
        Toast.makeText(this, "Payment Failed: $message", Toast.LENGTH_SHORT).show()
    }
}