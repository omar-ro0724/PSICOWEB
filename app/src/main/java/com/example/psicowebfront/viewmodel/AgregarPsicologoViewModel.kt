package com.example.psicowebfront.Screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psicowebfront.Modelo.Usuario
import com.example.psicowebfront.Network.RetrofitCliente
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AgregarPsicologoViewModel : ViewModel() {
    private val _mensaje = MutableStateFlow<String?>(null)
    val mensaje: StateFlow<String?> = _mensaje

    fun registrarPsicologo(nombre: String, apellido: String, correo: String, contrasena: String) {
        viewModelScope.launch {
            try {
                val usuario = Usuario(
                    id = 0,
                    nombre = nombre,
                    apellido = apellido,
                    correo = correo,
                    contrasena = contrasena,
                    rol = "psicologo"
                )
                val response = RetrofitCliente.instance.registrarUsuario(usuario)
                if (response.isSuccessful) {
                    _mensaje.value = "Psicólogo registrado correctamente"
                } else {
                    _mensaje.value = "Error al registrar: ${response.code()}"
                }
            } catch (e: Exception) {
                _mensaje.value = "Error: ${e.localizedMessage}"
            }
        }
    }

    fun limpiarMensaje() {
        _mensaje.value = null
    }
}