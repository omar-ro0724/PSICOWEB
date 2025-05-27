package com.example.psicowebfront.repository

import com.example.psicowebfront.Modelo.PsicologoRequest
import com.example.psicowebfront.Modelo.PsicologoResponse
import com.example.psicowebfront.Network.ApiService

import retrofit2.Response
import javax.inject.Inject


class PsicologoRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun crearPsicologo(psicologo: PsicologoRequest): Response<Void> =
        apiService.registrarPsicologo(psicologo)

    suspend fun obtenerPsicologos(): Response<List<PsicologoResponse>> =
        apiService.getAllPsicologos()
}