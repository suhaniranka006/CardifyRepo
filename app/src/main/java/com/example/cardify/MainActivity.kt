package com.example.cardify

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)


        findViewById<Button>(R.id.btnVendorLogin).setOnClickListener {
            startActivity(Intent(this, VendorRegistrationActivity::class.java))
        }

        findViewById<Button>(R.id.btnCoupleLogin).setOnClickListener {
            startActivity(Intent(this, CoupleRequirementActivity::class.java))
        }




    }
    }
