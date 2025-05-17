package com.example.psicowebfront.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AgendaPsicologoScreen() {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Agenda Detallada", style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(8.dp))
        Text("Aquí se conectará con la API para mostrar citas del psicólogo")
    }
}
