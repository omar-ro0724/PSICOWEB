package com.example.psicowebfront.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psicowebfront.Modelo.Psicologo
import com.example.psicowebfront.Modelo.PsicologoResponse
import com.example.psicowebfront.repository.PsicologoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class PsicologoViewModel @Inject constructor(
    private val repository: PsicologoRepository
) : ViewModel() {

    var psicologos: Response<List<PsicologoResponse>> by mutableStateOf<List<Psicologo>>(emptyList())
        private set

    var loading by mutableStateOf(false)
        private set

    fun obtenerPsicologos() {
        viewModelScope.launch {
            loading = true
            psicologos = repository.obtenerPsicologos()
            loading = false
        }
    }
}
