package com.example.psicowebfront.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psicowebfront.Modelo.PsicologoResponse
import com.example.psicowebfront.repository.PsicologoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PsicologoViewModel @Inject constructor(
    private val repository : PsicologoRepository
) : ViewModel() {

    private val _psicologos = MutableStateFlow<List<PsicologoResponse>>(emptyList())
    val psicologos: StateFlow<List<PsicologoResponse>> = _psicologos.asStateFlow()

    fun obtenerPsicologos() {
        viewModelScope.launch {
            val response = repository.obtenerPsicologos()
            if (response.isSuccessful) {
                _psicologos.value = repository.obtenerPsicologos().body() ?: emptyList()
            }
        }

    }
}
