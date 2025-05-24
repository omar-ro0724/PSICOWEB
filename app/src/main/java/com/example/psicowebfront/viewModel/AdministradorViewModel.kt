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
import com.example.psicowebfront.Repository.AdminRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class AdministradorViewModel @Inject constructor(
    private val repository: AdminRepository) : ViewModel() {

    var usuarios: Response<List<UsuarioResponse>> by mutableStateOf<List<Usuario>>(emptyList())
    var psicologos: Response<List<PsicologoResponse>> by mutableStateOf<List<Psicologo>>(emptyList())

    var loading by mutableStateOf(false)

    fun cargarUsuarios() {
        viewModelScope.launch {
            loading = true
            usuarios = repository.obtenerTodosLosUsuarios()
            loading = false
        }
    }

    fun cargarPsicologos() {

        viewModelScope.launch {
            loading = true
            psicologos = repository.obtenerTodosLosPsicologos()
            loading = false
        }
    }

    fun eliminarUsuario(id: Long) {
        viewModelScope.launch {
            repository.eliminarUsuario(id)
            cargarUsuarios()
        }
    }

    fun eliminarPsicologo(id: Long) {
        viewModelScope.launch {
            repository.eliminarPsicologo(id)
            cargarPsicologos()
        }
    }
}
