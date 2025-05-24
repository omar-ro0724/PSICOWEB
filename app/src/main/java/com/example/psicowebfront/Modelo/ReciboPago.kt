package com.example.psicowebfront.Modelo

import java.time.LocalDate

data class ReciboPago(

    val referencia: String,
    val valor : Double = 60000.0,
    val fechaEmision :LocalDate
)

