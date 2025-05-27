package com.example.psicowebfront.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.psicowebfront.Network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(    private val apiService: ApiService
) : ViewModel() {

    var currentUserId by mutableStateOf<Long?>(null)
    var currentRole by mutableStateOf<String>("")
    var currentUserEmail by mutableStateOf("")

    fun setSession(id: Long, email: String, rol: String) {
        currentUserId = id
        currentUserEmail = email
        currentRole = rol
    }

    fun logout() {
        currentUserId = null
        currentUserEmail = ""
        currentRole = ""
    }
}