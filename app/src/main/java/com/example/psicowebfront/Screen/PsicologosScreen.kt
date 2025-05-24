package com.example.psicowebfront.Screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.psicowebfront.viewModel.PsicologoViewModel

@Composable
fun PsicologosScreen(navController: NavController, viewModel: PsicologoViewModel = viewModel()) {
    val psicologos by viewModel.psicologos.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.obtenerPsicologos()
    }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Psicólogos disponibles", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(12.dp))

        psicologos.forEach { Psicologo ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                elevation = 4.dp
            ) {
                Column(Modifier.padding(16.dp)) {
                    Text("${psicologo.nombre} ${psicologo.apellido}")
                    Text("Correo: ${psicologo.correo}")
                    Button(
                        onClick = {
                            navController.navigate("agendar/${psicologo.id}")
                        },
                        modifier = Modifier.padding(top = 8.dp)
                    ) {
                        Text("Agendar cita")
                    }
                }
            }
        }
    }
}
