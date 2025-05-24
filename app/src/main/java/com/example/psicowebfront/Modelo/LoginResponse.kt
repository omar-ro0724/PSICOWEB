package com.example.psicowebfront.Modelo

data class LoginResponse(
    val token: String,
    val rol: String,
    val usuarioId: Long
)
