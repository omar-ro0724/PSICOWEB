package com.example.psicowebfront.Repository


import com.example.psicowebfront.Network.ApiService
import com.example.psicowebfront.Modelo.PsicologoResponse
import com.example.psicowebfront.Modelo.UsuarioResponse
import retrofit2.Response

class AdminRepository(private val apiService: ApiService) {

    suspend fun obtenerTodosLosUsuarios(): Response<List<UsuarioResponse>> {
        return apiService.getAllUsuarios()
    }

    suspend fun obtenerTodosLosPsicologos(): Response<List<PsicologoResponse>> {
        return apiService.getAllPsicologos()
    }

    suspend fun eliminarUsuario(id: Long): Response<Void> {
        return apiService.eliminarUsuario(id)
    }

    suspend fun eliminarPsicologo(id: Long): Response<Void> {
        return apiService.deletePsicologo(id)
    }
}
