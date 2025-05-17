package com.example.psicowebfront.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun AdminHomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Panel de Administración", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { navController.navigate("agregar_psicologo") }) {
            Text("Agregar Psicólogos")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("ver_usuarios") }) {
            Text("Ver Usuarios")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("gestionar_permisos") }) {
            Text("Gestionar Permisos")
        }
    }
}
