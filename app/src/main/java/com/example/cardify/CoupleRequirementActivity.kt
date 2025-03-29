package com.example.cardify

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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
