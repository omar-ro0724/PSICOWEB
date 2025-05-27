package com.example.psicowebfront.viewModel


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psicowebfront.Modelo.CitaRequest
import com.example.psicowebfront.Modelo.CitaResponse
import com.example.psicowebfront.Modelo.Disponibilidad
import com.example.psicowebfront.Modelo.PsicologoResponse
import com.example.psicowebfront.repository.CitaRepository
import com.example.psicowebfront.repository.DisponibilidadRepository
import com.example.psicowebfront.repository.PsicologoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgendarCitaViewModel @Inject constructor(
    private val citaRepository: CitaRepository,
    private val psicologoRepository: PsicologoRepository,
    private val disponibilidadRepository: DisponibilidadRepository
) : ViewModel() {

    private val _psicologos = MutableStateFlow<List<PsicologoResponse>>(emptyList())
    val psicologos: StateFlow<List<PsicologoResponse>> = _psicologos.asStateFlow()

    private val _disponibilidades = MutableStateFlow<List<Disponibilidad>>(emptyList())
    val disponibilidades: StateFlow<List<Disponibilidad>> = _disponibilidades.asStateFlow()

    private val _citaAgendada = MutableStateFlow<CitaResponse?>(null)
    val citaAgendada: StateFlow<CitaResponse?> = _citaAgendada.asStateFlow()

    var loading by mutableStateOf(false)
        private set

    fun obtenerPsicologos() {
        viewModelScope.launch {
            loading = true
            val response = psicologoRepository.obtenerPsicologos()
            if (response.isSuccessful) {
                _psicologos.value = response.body() ?: emptyList()
            }
            loading = false
        }
    }

    fun obtenerDisponibilidades(psicologoId: Int) {
        viewModelScope.launch {
            loading = true
            val response = disponibilidadRepository.getDisponibilidadesPorPsicologo(psicologoId)
            if (response.isSuccessful) {
                _disponibilidades.value = response.body() ?: emptyList()
            }
            loading = false
        }
    }

    fun agendarCita(request: CitaRequest, onSuccess: (CitaResponse) -> Unit) {
        viewModelScope.launch {
            loading = true
            val response = citaRepository.agendarCita(request)
            if (response.isSuccessful && response.body() != null) {
                _citaAgendada.value = response.body()
                onSuccess(response.body()!!)
            }
            loading = false
        }
    }
}
