package com.example.psicowebfront.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psicowebfront.Modelo.PsicologoRequest
import com.example.psicowebfront.repository.PsicologoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AgregarPsicologoViewModel @Inject constructor(
    private val repository: PsicologoRepository
) : ViewModel() {

    fun registrarPsicologo(
        nombre: String,
        apellido: String,
        correo: String,
        contrasena: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val psicologo = PsicologoRequest(
            nombre = nombre,
            apellido = apellido,
            correo = correo,
            contrasena = contrasena
        )

        viewModelScope.launch {
            try {
                val response = repository.crearPsicologo(psicologo)
                if (response.isSuccessful) {
                    onSuccess()
                } else {
                    onError("Error ${response.code()}: ${response.message()}")
                }
            } catch (e: Exception) {
                onError("Excepción: ${e.localizedMessage}")
            }
        }
    }
}
