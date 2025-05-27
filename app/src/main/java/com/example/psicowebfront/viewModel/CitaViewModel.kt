package com.example.psicowebfront.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psicowebfront.Modelo.CitaResponse
import com.example.psicowebfront.Modelo.CitaRequest
import com.example.psicowebfront.repository.CitaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitaViewModel @Inject constructor(
    private val repository: CitaRepository
) : ViewModel() {

    var citas by mutableStateOf<List<CitaResponse>>(emptyList())
        private set

    var loading by mutableStateOf(false)
        private set

    fun agendarCita(request: CitaRequest) {
        viewModelScope.launch {
            loading = true
            repository.agendarCita(request)
            loading = false
        }
    }

    fun obtenerCitasPorUsuario(idUsuario: Long) {
        viewModelScope.launch {
            loading = true
            val response = repository.citasPorUsuario(idUsuario)
            if (response.isSuccessful) {
                citas = response.body() ?: emptyList()
            }
            loading = false
        }
    }

    fun obtenerCitasPorPsicologo(psicologoId: Long) {
        viewModelScope.launch {
            loading = true
            val response = repository.citasPorPsicologo(psicologoId)
            if (response.isSuccessful) {
                citas = response.body() ?: emptyList()
            }
            loading = false
        }
    }
}

