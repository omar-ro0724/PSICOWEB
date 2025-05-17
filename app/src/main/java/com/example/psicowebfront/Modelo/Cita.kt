package com.example.psicowebfront.Modelo

data class Cita(
    val idCita: Long? = null,
    val idUsuario: Long,
    val idPsicologo: Long,
    val fecha: String,
    val hora: String,
    val estado: String,
    val usuario: Usuario,
    val disponibilidad: Disponibilidad
)
