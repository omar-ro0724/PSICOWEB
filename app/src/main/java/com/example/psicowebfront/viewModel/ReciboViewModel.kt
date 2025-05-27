package com.example.psicowebfront.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psicowebfront.Modelo.ReciboPago
import com.example.psicowebfront.Network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReciboViewModel @Inject constructor(
    private val apiService: ApiService,
) : ViewModel() {

    var recibo by mutableStateOf<ReciboPago?>(null)
        private set

    var loading by mutableStateOf(false)
        private set

    fun obtenerReciboPorCita(citaId: Long) {
        viewModelScope.launch {
            loading = true
            val response = apiService.obtenerRecibo(citaId)
            if (response.isSuccessful) {
                recibo = response.body()
            }
            loading = false
        }
    }
}


