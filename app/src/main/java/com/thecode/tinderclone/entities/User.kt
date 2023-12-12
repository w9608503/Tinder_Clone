package com.thecode.tinderclone.entities

data class User(
    val username: String? = null,
    val email: String? = null,
    val address: String? = null,
    val imageUrl: String? = null // Add this field for storing the image URL
)
