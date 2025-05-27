package com.example.psicowebfront.repository

import com.example.psicowebfront.Modelo.Disponibilidad
import com.example.psicowebfront.Network.ApiService
import retrofit2.Response
import javax.inject.Inject

class DisponibilidadRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun obtenerDisponibilidad(psicologoId: Long): Response<List<String>> =
        apiService.obtenerDisponibilidad(psicologoId)

    suspend fun getDisponibilidadesPorPsicologo(id: Int): Response<List<Disponibilidad>> =
        apiService.getDisponibilidadesPorPsicologo(id)
}