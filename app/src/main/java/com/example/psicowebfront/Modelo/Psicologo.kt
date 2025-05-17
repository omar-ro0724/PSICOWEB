package com.example.psicowebfront.Modelo

data class Psicologo(
    val nombre: String,
    val idPsicologo: Long,
    val usuario: Usuario,
    val especialidad: String,
    val tarifa: Double
)