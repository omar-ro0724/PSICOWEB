package com.example.psicowebfront.Repository

import com.example.psicowebfront.Modelo.Cita
import com.example.psicowebfront.Modelo.CitaResponse
import com.example.psicowebfront.Network.ApiService
import com.example.psicowebfront.Modelo.CitaRequest
import retrofit2.Response
import retrofit2.http.Path
import javax.inject.Inject

class CitaRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun agendarCita(citaRequest: CitaRequest): Response<CitaResponse> {
        return apiService.agendarCita(citaRequest)
    }

    suspend fun citasPorUsuario(userId: Long): Response<List<CitaResponse>> {
        return apiService.citasPorUsuario(userId)
    }

    suspend fun citasPorPsicologo(@Path("id") id: Long): Response<List<Cita>> {
        return apiService.citasPorPsicologo(id)
    }
}