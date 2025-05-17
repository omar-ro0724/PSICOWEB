package com.example.psicowebfront.Modelo

data class Disponibilidad(
    val idDisp: Long,
    val fecha: String,
    val horaInicio: String,
    val horaFin: String,
    val estado: String,
    val psicologo: Psicologo
)