package com.example.psicowebfront.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psicowebfront.Modelo.LoginRequest
import com.example.psicowebfront.Modelo.Usuario
import com.example.psicowebfront.Network.RetrofitCliente
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {

    private val _usuarioActual = MutableStateFlow<Usuario?>(null)
    val usuarioActual = _usuarioActual.asStateFlow()

    var errorLogin: String? = null
        private set

    fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                val loginRequest = LoginRequest(correo = email, contrasena = password)
                val response = RetrofitCliente.instance.login(loginRequest)
                if (response.isSuccessful) {
                    _usuarioActual.value = response.body()
                    onResult(true)
                } else {
                    _usuarioActual.value = null
                    onResult(false)
                }
            } catch (e: Exception) {
                _usuarioActual.value = null
                onResult(false)
            }
        }
    }


    fun registrar(usuario: Usuario, onResult: (Boolean, String?) -> Unit) {
        viewModelScope.launch {
            try {
                val response = RetrofitCliente.instance.registrarUsuario(usuario)
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
