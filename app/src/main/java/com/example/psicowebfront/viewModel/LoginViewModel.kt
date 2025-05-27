package com.example.psicowebfront.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psicowebfront.Modelo.LoginRequest
import com.example.psicowebfront.Modelo.Usuario
import com.example.psicowebfront.Network.ApiService
import com.example.psicowebfront.Network.RetrofitCliente
import com.example.psicowebfront.repository.UsuarioRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {
    private val _usuarioActual = MutableStateFlow<Usuario?>(null)
    val usuarioActual: StateFlow<Usuario?> = _usuarioActual

    private val _errorLogin = MutableStateFlow<String?>(null)
    val errorLogin: StateFlow<String?> = _errorLogin

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

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


    fun limpiarError() {
        _errorLogin.value = null
    }

    fun logout() {
        _usuarioActual.value = null
    }
}