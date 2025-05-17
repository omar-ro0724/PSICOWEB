package com.example.psicowebfront.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.psicowebfront.Modelo.Usuario

@Composable
fun LoginScreen(navController: NavController, loginViewModel: LoginViewModel = viewModel()) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val usuario = loginViewModel.usuarioActual

    if (usuario != null) {
        // Navegar al home según rol
        LaunchedEffect(usuario) {
            when (usuario.rol) {
                "usuario" -> navController.navigate("usuario_home") { popUpTo("login") { inclusive = true } }
                "psicologo" -> navController.navigate("psicologo_home") { popUpTo("login") { inclusive = true } }
                "admin" -> navController.navigate("admin_home") { popUpTo("login") { inclusive = true } }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("Iniciar sesión", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { loginViewModel.login(email, password) }, modifier = Modifier.fillMaxWidth()) {
            Text("Entrar")
        }
    }
}

class LoginViewModel : ViewModel() {
    var usuarioActual by mutableStateOf<Usuario?>(null)
        private set

    fun login(email: String, password: String) {

        if(email == "test@example.com" && password == "1234") {
            usuarioActual = Usuario(
                1,
                "test",
                "test",
                "test@example.com",
                "1234",
                "admin",
            )
        } else {
            usuarioActual = null
        }
    }

    fun logout() {
        usuarioActual = null
    }
}
