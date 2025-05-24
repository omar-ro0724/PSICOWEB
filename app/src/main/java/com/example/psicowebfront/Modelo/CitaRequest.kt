package com.example.psicowebfront.Modelo

import java.time.LocalDate
import java.time.LocalTime

data class CitaRequest(
    val psicologoId: Long,
    val pacienteId: Long,
    val fecha: LocalDate,
    val hora: LocalTime
)
