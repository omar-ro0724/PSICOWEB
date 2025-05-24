package com.example.psicowebfront.Modelo

import java.time.LocalDate
import java.time.LocalTime

data class Disponibilidad(
    val idDisp: Long,
    val fecha: LocalDate,
    val horaInicio: LocalTime,
    val horaFin: String,
    val estado: String,
    val psicologo: Psicologo
)