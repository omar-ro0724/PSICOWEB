package com.example.psicowebfront.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psicowebfront.Modelo.Psicologo
import com.example.psicowebfront.Modelo.PsicologoResponse
import com.example.psicowebfront.Modelo.Usuario
import com.example.psicowebfront.Modelo.UsuarioResponse
import com.example.psicowebfront.repository.AdminRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AdministradorViewModel @Inject constructor(
    private val repository: AdminRepository
) : ViewModel() {
    var usuarios by mutableStateOf<List<UsuarioResponse>>(emptyList())
        private set

    var psicologos by mutableStateOf<List<PsicologoResponse>>(emptyList())
        private set

    var loading by mutableStateOf(false)
        private set

    fun cargarUsuarios() {
        viewModelScope.launch {
            loading = true
            val response = repository.obtenerTodosLosUsuarios()
            if (response.isSuccessful) {
                usuarios = response.body() ?: emptyList()
            }
            loading = false
        }
    }

    fun cargarPsicologos() {
        viewModelScope.launch {
            loading = true
            val response = repository.obtenerTodosLosPsicologos()
            if (response.isSuccessful) {
                psicologos = response.body() ?: emptyList()
            }
            loading = false
        }
    }
}
