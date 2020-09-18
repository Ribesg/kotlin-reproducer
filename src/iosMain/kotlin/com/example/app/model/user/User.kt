package com.example.app.model.user

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Long,
    val name: String,
    val username: String,
    val email: String
)
