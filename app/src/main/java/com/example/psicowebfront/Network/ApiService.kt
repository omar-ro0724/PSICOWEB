package com.example.psicowebfront.Network

import com.example.psicowebfront.Modelo.Cita
import com.example.psicowebfront.Modelo.CitaRequest
import com.example.psicowebfront.Modelo.CitaResponse
import com.example.psicowebfront.Modelo.Disponibilidad
import com.example.psicowebfront.Modelo.LoginRequest
import com.example.psicowebfront.Modelo.LoginResponse
import com.example.psicowebfront.Modelo.Psicologo
import com.example.psicowebfront.Modelo.PsicologoRequest
import com.example.psicowebfront.Modelo.PsicologoResponse
import com.example.psicowebfront.Modelo.ReciboPago
import com.example.psicowebfront.Modelo.RegistroRequest
import com.example.psicowebfront.Modelo.RegistroResponse
import com.example.psicowebfront.Modelo.Usuario
import com.example.psicowebfront.Modelo.UsuarioResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import retrofit2.http.*

interface ApiService {

    @POST("api/usuarios/registro")
    suspend fun registrarUsuario(@Body usuario: Usuario): Response<Usuario>

    @GET("api/usuarios/listar")
    suspend fun listarUsuarios(): Response<List<Usuario>>

    @GET("api/usuarios/buscar")
    suspend fun obtenerUsuarioPorEmail(@Query("email") email: String): Response<Usuario>

    @POST("api/citas/agendar")
    suspend fun agendarCita(@Body citaRequest: CitaRequest): Response<CitaResponse>

    @GET("api/citas/disponibilidades/{idPsicologo}")
    suspend fun getDisponibilidadesPorPsicologo(@Path("idPsicologo") id: Int): List<Disponibilidad>

    @GET("api/citas/psicologo/{id}")
    suspend fun citasPorPsicologo(@Path("id") id: Long): Response<List<Cita>>

    @GET("api/usuarios")
    suspend fun getAllUsuarios(): Response<List<UsuarioResponse>>

    @GET("api/psicologos")
    suspend fun getAllPsicologos(): Response<List<PsicologoResponse>>

    @DELETE("api/usuarios/{id}")
    suspend fun eliminarUsuario(@Path("id") id: Long): Response<Void>

    @DELETE("api/psicologos/{id}")
    suspend fun deletePsicologo(@Path("id") id: Long): Response<Void>

    @GET("api/disponibilidad/{id}")
    suspend fun obtenerDisponibilidad(@Path("id") psicologoId: Long): Response<List<String>>
    @GET("psicologos")
    suspend fun listarPsicologos(): Response<List<Psicologo>>

    @GET("api/citas/usuario/{id}")
    suspend fun citasPorUsuario(@Path("id") id: Long): Response<List<CitaResponse>>

    @GET("api/recibos/{idCita}")
    suspend fun generarRecibo(@Path("idCita") id: Long): Response<ReciboPago>

    @POST("api/usuarios/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("/psicologos")
    suspend fun registrarPsicologo(@Body psicologo: PsicologoRequest): Response<Void>

}
