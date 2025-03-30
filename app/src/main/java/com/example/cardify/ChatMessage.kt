package com.example.cardify

data class ChatMessage(
    val text: String,
    val senderId: String,
    val timestamp: Long
)