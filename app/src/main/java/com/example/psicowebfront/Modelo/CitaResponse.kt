package com.example.psicowebfront.Modelo


data class CitaResponse(
    val id: Long,
    val fecha: String,
    val hora: String,
    val usuarioId: Long,
    val psicologoId: Long,
)
