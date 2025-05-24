package com.example.psicowebfront.Repository

import com.example.psicowebfront.Modelo.Disponibilidad
import com.example.psicowebfront.Network.ApiService
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.Path
import javax.inject.Inject

class DisponibilidadRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun obtenerDisponibilidad(@Path("id") psicologoId: Long): retrofit2.Response<List<String>>{
        return apiService.obtenerDisponibilidad(psicologoId)
    }

    suspend fun getDisponibilidadesPorPsicologo(@Path("idPsicologo") id: Int): List<Disponibilidad>{
        return apiService.getDisponibilidadesPorPsicologo(id)
    }

}
