package com.example.psicowebfront.repository


import com.example.psicowebfront.Network.ApiService
import com.example.psicowebfront.Modelo.PsicologoResponse
import com.example.psicowebfront.Modelo.UsuarioResponse
import retrofit2.Response
import javax.inject.Inject

class AdminRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun obtenerTodosLosUsuarios(): Response<List<UsuarioResponse>> =
        apiService.getAllUsuarios()

    suspend fun obtenerTodosLosPsicologos(): Response<List<PsicologoResponse>> =
        apiService.getAllPsicologos()

    suspend fun eliminarUsuario(id: Long): Response<Void> =
        apiService.eliminarUsuario(id)

    suspend fun eliminarPsicologo(id: Long): Response<Void> =
        apiService.deletePsicologo(id)
}