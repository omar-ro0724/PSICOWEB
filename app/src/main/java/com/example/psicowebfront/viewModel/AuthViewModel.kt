package com.example.psicowebfront.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psicowebfront.Modelo.LoginRequest
import com.example.psicowebfront.Modelo.Usuario
import com.example.psicowebfront.Network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(    private val apiService: ApiService

) : ViewModel() {

    private val _usuarioActual = MutableStateFlow<Usuario?>(null)
    val usuarioActual = _usuarioActual.asStateFlow()

    var errorLogin: String? = null
        private set

    fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val loginRequest = LoginRequest(correo = email, contrasena = password)
            val response = apiService.login(loginRequest)
            if (response.isSuccessful) {
                val loginResponse = response.body()
                if (loginResponse != null) {
                    _usuarioActual.value = Usuario(
                        id = loginResponse.usuarioId.toInt(),
                        nombre = "",
                        apellido = "",
                        correo = email,
                        contrasena = password,
                        rol = loginResponse.rol
                    )
                }
                onResult(true)
            } else {
                _usuarioActual.value = null
                onResult(false)
            }
        }
    }


    fun registrar(usuario: Usuario, onResult: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            try {
                val response = apiService.registrarUsuario(usuario)
                if (response.isSuccessful) {
                    onResult(true, null)
                } else {
                    val error = response.errorBody()?.string()
                    Log.e("Registro", "Error: $error")
                    onResult(false, error)
                }
            } catch (e: Exception) {
                Log.e("Registro", "Excepción: ${e.message}")
                onResult(false, e.message)
            }
        }
    }
}
