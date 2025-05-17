package com.example.psicowebfront.Screen

import androidx.compose.foundation.clickable
import com.example.psicowebfront.Network.RetrofitCliente
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.psicowebfront.Modelo.Cita
import com.example.psicowebfront.Modelo.Disponibilidad
import com.example.psicowebfront.Modelo.Usuario
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgendarCitaScreen(navController: NavController, psicologoId: Int) {
    val scope = rememberCoroutineScope()
    var disponibilidades by remember { mutableStateOf(emptyList<Disponibilidad>()) }
    var selectedDisp by remember { mutableStateOf<Disponibilidad?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }
    var isBooking by remember { mutableStateOf(false) }

    LaunchedEffect(psicologoId) {
        isLoading = true
        error = null
        scope.launch {
            val result = RetrofitCliente.instance.getDisponibilidadesPorPsicologo(psicologoId)

            try {

                disponibilidades = RetrofitCliente.instance.getDisponibilidadesPorPsicologo(psicologoId)
                isLoading = false
            } catch (e: Exception) {
                error = "Error al cargar disponibilidades: ${e.message}"
                isLoading = false
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Agendar Cita") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                when {
                    isLoading -> {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                CircularProgressIndicator()
                                Text("Cargando disponibilidades...")
                            }
                        }
                    }
                    error != null -> {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("Error: $error", color = MaterialTheme.colorScheme.error)
                        }
                    }
                    disponibilidades.isEmpty() -> {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("No hay disponibilidades para este psicólogo.")
                        }
                    }
                    else -> {
                        Text("Selecciona una disponibilidad:", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(bottom = 8.dp))
                        LazyColumn(modifier = Modifier.weight(1f)) { // Permite que la lista ocupe el espacio restante
                            items(disponibilidades) { disp ->
                                DisponibilidadItem(
                                    disponibilidad = disp,
                                    isSelected = disp == selectedDisp,
                                    onSelect = { selectedDisp = disp }
                                )
                            }
                        }

                        selectedDisp?.let { selected ->
                            Button(
                                onClick = {
                                    isBooking = true
                                    scope.launch {
                                        try {
                                            val currentUser = Usuario(
                                                id = 1,
                                                nombre = "Juan",
                                                apellido = "Bustos",
                                                correo = "juan@correo.com",
                                                contrasena = "Angelito",
                                                rol = "paciente",

                                            )
                                            val cita = Cita(
                                                idCita = null,
                                                fecha = "Fecha_Actual",
                                                estado = "Agendada",
                                                usuario = currentUser,
                                                disponibilidad = selected,
                                                hora = "Hora_Actual",
                                                idUsuario = 1,
                                                idPsicologo = 1
                                            )

                                            RetrofitCliente.instance.agendarCita(cita)
                                            navController.navigate("home") {

                                                popUpTo("home") { inclusive = true }
                                            }
                                        } catch (e: Exception) {
                                            error = "Error al agendar cita: ${e.message}"
                                        } finally {
                                            isBooking = false
                                        }
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 16.dp),
                                enabled = !isBooking
                            ) {
                                if (isBooking) {
                                    CircularProgressIndicator(modifier = Modifier.size(24.dp), color = MaterialTheme.colorScheme.onPrimary)
                                    Spacer(Modifier.width(8.dp))
                                    Text("Agendando...")
                                } else {
                                    Text("Confirmar Cita")
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun DisponibilidadItem(
    disponibilidad: Disponibilidad,
    isSelected: Boolean,
    onSelect: (Disponibilidad) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable { onSelect(disponibilidad) },
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(text = "Fecha: ${disponibilidad.fecha}", style = MaterialTheme.typography.bodyMedium)
            }
        }
    }
}
