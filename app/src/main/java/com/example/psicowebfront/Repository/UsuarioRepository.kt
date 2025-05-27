package com.example.psicowebfront.repository

import android.view.PixelCopy.request
import com.example.psicowebfront.Modelo.LoginRequest
import com.example.psicowebfront.Modelo.LoginResponse
import com.example.psicowebfront.Modelo.RegistroResponse
import com.example.psicowebfront.Modelo.Usuario
import com.example.psicowebfront.Modelo.UsuarioResponse
import com.example.psicowebfront.Network.ApiService
import okhttp3.Response
import retrofit2.http.Body
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject

class UsuarioRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun login(@Body request: LoginRequest): retrofit2.Response<LoginResponse> {
        return apiService.login(request)
    }

    suspend fun registrarUsuario(@Body usuario: Usuario): retrofit2.Response<Usuario> {
        return apiService.registrarUsuario(usuario)
    }

    suspend fun listarUsuarios(): retrofit2.Response<List<Usuario>> {
        return apiService.listarUsuarios()
    }

    suspend fun eliminarUsuario(@Path("id") id: Long): retrofit2.Response<Void> {
        return apiService.eliminarUsuario(id)
    }

    suspend fun obtenerUsuarioPorEmail(@Query("email") email: String): retrofit2.Response<Usuario> {
        return apiService.obtenerUsuarioPorEmail(email)
    }

    suspend fun getAllUsuarios(): retrofit2.Response<List<UsuarioResponse>> {
        return apiService.getAllUsuarios()

    }
}