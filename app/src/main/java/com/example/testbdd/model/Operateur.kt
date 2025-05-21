package com.example.testbdd.model

data class Operateur(
    val id: Int,
    val nom: String,
    val role: String,
    val created_at: String? = null
) 