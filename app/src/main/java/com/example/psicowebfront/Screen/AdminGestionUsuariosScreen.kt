package com.example.psicowebfront.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
@Composable
fun AgregarPsicologoScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Formulario de Psicólogo", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Aquí irá el formulario para registrar un nuevo psicólogo")
    }
}

@Composable
fun GestionarPermisosScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Permisos de Usuario", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Aquí se podrán cambiar los permisos de los usuarios")
    }
}

@Composable
fun VerUsuariosScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Usuarios Registrados", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Aquí se mostrará la lista de usuarios con sus roles")
    }
}


