package com.example.psicowebfront.repository

import com.example.psicowebfront.Modelo.PsicologoRequest
import com.example.psicowebfront.Modelo.PsicologoResponse
import retrofit2.Response
import javax.inject.Inject

class PsicologoRepository @Inject constructor()
{
    suspend fun crearPsicologo(psicologo: PsicologoRequest): Response<Void> {
        return apiService.registrarPsicologo(psicologo)
    }
    suspend fun obtenerPsicologos(): Response<List<PsicologoResponse>> {
        return apiService.getAllPsicologos()
    }
}
