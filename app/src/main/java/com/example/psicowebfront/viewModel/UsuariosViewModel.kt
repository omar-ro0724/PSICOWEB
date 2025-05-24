package com.example.psicowebfront.Screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psicowebfront.Modelo.Usuario
import com.example.psicowebfront.Network.RetrofitCliente
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UsuariosViewModel : ViewModel() {
    private val _usuarios = MutableStateFlow<List<Usuario>>(emptyList())
    val usuarios: StateFlow<List<Usuario>> = _usuarios

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun cargarUsuarios() {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val response = RetrofitCliente.instance.listarUsuarios()
                if (response.isSuccessful) {
                    _usuarios.value = response.body() ?: emptyList()
                    _error.value = null
                } else {
                    _error.value = "Error al cargar usuarios: ${response.code()}"
                }
            } catch (e: Exception) {
                _error.value = "Error de red: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }
}
