package com.example.psicowebfront.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psicowebfront.Modelo.Cita
import com.example.psicowebfront.Modelo.CitaRequest
import com.example.psicowebfront.Repository.CitaRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
    class CitaViewModel @Inject constructor(
        private val repository: CitaRepository
    ) : ViewModel() {

        var citas: Response<List<Cita>> by mutableStateOf<List<Cita>>(emptyList())
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
                citas = repository.citasPorUsuario(idUsuario)
                loading = false
            }
        }

        fun obtenerCitasPorPsicologo(psicologoId: Long) {
            viewModelScope.launch {
                loading = true
                citas = repository.citasPorPsicologo(psicologoId)
                loading = false
            }
        }
    }

