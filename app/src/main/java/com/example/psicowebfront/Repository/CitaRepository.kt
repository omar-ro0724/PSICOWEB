package com.example.psicowebfront.repository

import com.example.psicowebfront.Modelo.CitaResponse
import com.example.psicowebfront.Network.ApiService
import com.example.psicowebfront.Modelo.CitaRequest
import retrofit2.Response
import javax.inject.Inject

class CitaRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun agendarCita(citaRequest: CitaRequest): Response<CitaResponse> =
        apiService.agendarCita(citaRequest)

    suspend fun citasPorUsuario(userId: Long): Response<List<CitaResponse>> =
        apiService.citasPorUsuario(userId)

    suspend fun citasPorPsicologo(id: Long): Response<List<CitaResponse>> =
        apiService.citasPorPsicologo(id)
}