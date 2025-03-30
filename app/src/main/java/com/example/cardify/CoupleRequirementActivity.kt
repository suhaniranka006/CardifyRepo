package com.example.cardify

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class CoupleRequirementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_couple_requirement)


        findViewById<Button>(R.id.btnPost).setOnClickListener {
            val name = findViewById<EditText>(R.id.etName).text.toString()
            val deadline = findViewById<EditText>(R.id.etDeadline).text.toString()
            val budget = findViewById<EditText>(R.id.etBudget).text.toString()
            val requirements = findViewById<EditText>(R.id.etRequirements).text.toString()

            if (name.isNotEmpty() && deadline.isNotEmpty() && budget.isNotEmpty() && requirements.isNotEmpty()) {
                val intent = Intent(this, BidsListActivity::class.java).apply {
                    putExtra("name", name)
                    putExtra("deadline", deadline)
                    putExtra("budget", budget)
                    putExtra("requirements", requirements)
                    flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                }
                startActivity(intent)
                finish() // Finish current activity
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }
        }
    }



//class CoupleRequirementActivity : AppCompatActivity() {
//    private var selectedImageUri: Uri? = null
//    private lateinit var ivCardSample: ImageView
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_couple_requirement)
//
//        ivCardSample = findViewById(R.id.ivCardSample)
//
//        // Image selection
//        findViewById<Button>(R.id.btnAddImage).setOnClickListener {
//            val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
//                type = "image/*"
//            }
//            startActivityForResult(intent, 101)
//        }
//
//        // Your existing post button code
//        findViewById<Button>(R.id.btnPost).setOnClickListener {
//            postRequirement()
//        }
//    }
//
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        if (requestCode == 101 && resultCode == RESULT_OK) {
//            data?.data?.let { uri ->
//                selectedImageUri = uri
//                ivCardSample.setImageURI(uri)
//            }
//        }
//    }
//
//    private fun postRequirement() {
//        val name = findViewById<EditText>(R.id.etName).text.toString()
//        val deadline = findViewById<EditText>(R.id.etDeadline).text.toString()
//        val budget = findViewById<EditText>(R.id.etBudget).text.toString()
//        val requirements = findViewById<EditText>(R.id.etRequirements).text.toString()
//
//        if (name.isEmpty() || deadline.isEmpty() || budget.isEmpty() || requirements.isEmpty()) {
//            Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show()
//            return
//        }
//
//        // Proceed with or without image
//        saveRequirement(name, deadline, budget, requirements)
//    }
//
//    private fun saveRequirement(name: String, deadline: String, budget: String, requirements: String) {
//        // Your existing save logic
//        Toast.makeText(this, "Requirement posted!", Toast.LENGTH_SHORT).show()
//
//        // If you want to handle the image separately:
//        selectedImageUri?.let { uri ->
//            // Add your image processing here (optional)
//        }
//
//        finish()
//    }
//}
