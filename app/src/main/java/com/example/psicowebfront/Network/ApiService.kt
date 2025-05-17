package com.example.psicowebfront.Network

import com.example.psicowebfront.Modelo.Cita
import com.example.psicowebfront.Modelo.Disponibilidad
import com.example.psicowebfront.Modelo.LoginRequest
import com.example.psicowebfront.Modelo.Usuario
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.*

interface ApiService {

    @POST("api/usuarios/registro")
    suspend fun registrarUsuario(@Body usuario: Usuario): Response<Usuario>

    @POST("/api/usuarios/login")
    suspend fun login(@Body request: LoginRequest): Response<Usuario>

    @GET("/api/usuarios/listar")
    suspend fun listarUsuarios(): Response<List<Usuario>>
    @GET("api/usuarios/buscar")
    suspend fun obtenerUsuarioPorEmail(@Query("email") email: String): Response<Usuario>

    @POST("api/citas/agendar")
    suspend fun agendarCita(@Body cita: Cita): Response<Cita>

    @GET("api/citas/disponibilidades/{idPsicologo}")
    suspend fun getDisponibilidadesPorPsicologo(
        @Path("idPsicologo") id: Int
    ): List<Disponibilidad>


    @GET("/api/citas/psicologo/{id}")
    suspend fun citasPorPsicologo(@Path("id") id: Long): Response<List<Cita>>
}
