package com.example.psicowebfront.Screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.psicowebfront.Modelo.PsicologoResponse
import com.example.psicowebfront.viewModel.PsicologoViewModel

@Composable
fun PsicologosScreen(
    navController: NavController,
    viewModel: PsicologoViewModel = hiltViewModel()
) {
    val psicologos by viewModel.psicologos.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.obtenerPsicologos()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        item {
            Text(
                "Psicólogos disponibles",
                style = MaterialTheme.typography.headlineMedium
            )
            Spacer(modifier = Modifier.height(12.dp))
        }
        items(psicologos) { psicologo ->
            PsicologoCard(
                psicologo = psicologo,
                onAgendarClick = {
                    navController.navigate("agendar/${psicologo.id}")
                }
            )
        }
    }
}

@Composable
private fun PsicologoCard(
    psicologo: PsicologoResponse,
    onAgendarClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = psicologo.nombre)
            Text(text = "Especialidad: ${psicologo.especialidad}")
            Button(
                onClick = onAgendarClick,
                modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(text = "Agendar cita")
            }
        }
    }
}
