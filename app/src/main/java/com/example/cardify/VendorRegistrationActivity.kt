package com.example.cardify

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class VendorRegistrationActivity : AppCompatActivity() {

    private val selectedImages = mutableListOf<Uri>()
    private var imageContainer: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vendor_registration)

        imageContainer = findViewById(R.id.imageContainer)

        findViewById<Button>(R.id.btnAddImage).setOnClickListener {
            if (selectedImages.size < 3) {
                openImagePicker()
            } else {
                Toast.makeText(this, "Maximum 3 images allowed", Toast.LENGTH_SHORT).show()
            }
        }

        findViewById<Button>(R.id.btnSubmitVendor).setOnClickListener {
            validateAndSubmit()
        }
    }

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_GET_CONTENT).apply {
            type = "image/*"
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        }
        startActivityForResult(intent, PICK_IMAGES_REQUEST)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGES_REQUEST && resultCode == RESULT_OK) {
            data?.clipData?.let { clipData ->
                for (i in 0 until minOf(clipData.itemCount, 3 - selectedImages.size)) {
                    selectedImages.add(clipData.getItemAt(i).uri)
                }
            } ?: data?.data?.let {
                selectedImages.add(it)
            }
            updateImagePreview()
        }
    }

    private fun updateImagePreview() {
        imageContainer?.removeAllViews()

        selectedImages.forEachIndexed { index, uri ->
            val imageView = ImageView(this).apply {
                layoutParams = LinearLayout.LayoutParams(200, 200).apply {
                    marginEnd = 8
                }
                scaleType = ImageView.ScaleType.CENTER_CROP
                setImageURI(uri)

                setOnClickListener {
                    selectedImages.removeAt(index)
                    updateImagePreview()
                }
            }
            imageContainer?.addView(imageView)
        }
    }

    private fun validateAndSubmit() {
        val vendorName = findViewById<EditText>(R.id.etVendorName).text.toString()
        val companyName = findViewById<EditText>(R.id.etCompanyName).text.toString()

        if (vendorName.isEmpty() || companyName.isEmpty()) {
            Toast.makeText(this, "Please fill all text fields", Toast.LENGTH_SHORT).show()
            return
        }

        if (selectedImages.isEmpty()) {
            Toast.makeText(this, "Please add at least one work sample", Toast.LENGTH_SHORT).show()
            return
        }

        // Proceed to vendor dashboard
        startActivity(Intent(this, VendorDashboardActivity::class.java).apply {
            putExtra("vendor_name", vendorName)
            putExtra("company_name", companyName)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        })
    }

    companion object {
        private const val PICK_IMAGES_REQUEST = 101
    }
}