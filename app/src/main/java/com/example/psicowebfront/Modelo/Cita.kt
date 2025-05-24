package com.example.psicowebfront.Modelo

import java.time.LocalDate
import java.time.LocalTime

data class Cita(
    val idCita: Long? = null,
    val idUsuario: Long,
    val idPsicologo: Long,
    val fecha: LocalDate,
    val hora: LocalTime,
    val estado: String,
    val usuario: Usuario,
    val disponibilidad: Disponibilidad
)
