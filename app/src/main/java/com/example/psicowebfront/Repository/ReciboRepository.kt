package com.example.psicowebfront.Repository


import com.example.psicowebfront.Modelo.Psicologo
import com.example.psicowebfront.Modelo.PsicologoResponse
import com.example.psicowebfront.Network.ApiService
import com.example.psicowebfront.Modelo.ReciboPago
import retrofit2.Response
import javax.inject.Inject

class ReciboRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun obtenerRecibo(citaId: Long): Response<ReciboPago> {
        return apiService.generarRecibo(citaId)
    }

}

