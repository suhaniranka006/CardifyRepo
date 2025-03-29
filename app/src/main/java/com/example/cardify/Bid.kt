package com.example.cardify

data class Bid(
    val vendorName: String,
    val price: String,
    val deliveryTime: String,
    val proposal: String,
   // val status: String // "Pending", "Accepted", "Rejected"
    var isSelected: Boolean = false
)