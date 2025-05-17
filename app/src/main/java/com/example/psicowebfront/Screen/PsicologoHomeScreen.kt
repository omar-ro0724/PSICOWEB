package com.example.psicowebfront.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
@Composable
fun PsicologoHomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bienvenido Psicólogo", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(24.dp))

        Button(onClick = { navController.navigate("ver_agenda") }) {
            Text("Ver Agenda")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("cerrar_sesion") }) {
            Text("Cerrar Sesión")
        }
    }
}
