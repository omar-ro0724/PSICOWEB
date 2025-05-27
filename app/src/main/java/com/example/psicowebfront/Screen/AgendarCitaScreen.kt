package com.example.psicowebfront.Screen

import android.app.DatePickerDialog
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.psicowebfront.Modelo.CitaRequest
import com.example.psicowebfront.Modelo.PsicologoResponse
import com.example.psicowebfront.viewModel.AgendarCitaViewModel
import java.time.LocalDate
import java.time.LocalDate.of
import java.time.LocalTime
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AgendarCitaScreen(
    navController: NavController,
    viewModel: AgendarCitaViewModel = hiltViewModel()
) {
    val context = LocalContext.current

    var fechaSeleccionada by remember { mutableStateOf<LocalDate?>(null) }
    var horaSeleccionada by remember { mutableStateOf<LocalTime?>(null) }
    var psicologoSeleccionado by remember { mutableStateOf<PsicologoResponse?>(null) }

    val psicologos by viewModel.psicologos.collectAsState()
    val disponibilidades by viewModel.disponibilidades.collectAsState()
    val citaAgendada by viewModel.citaAgendada.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.obtenerPsicologos()
    }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text("Agendar Cita", style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(16.dp))

            DropdownMenuPsicologos(
                psicologos = psicologos,
                seleccionado = psicologoSeleccionado,
                onSeleccionar = { seleccionado ->
                    psicologoSeleccionado = seleccionado
                    seleccionado?.let {
                        viewModel.obtenerDisponibilidades(it.id.toInt())
                    }
                    fechaSeleccionada = null
                    horaSeleccionada = null
                }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                val calendar = Calendar.getInstance()
                DatePickerDialog(
                    context,
                    { _, year, month, dayOfMonth ->
                        fechaSeleccionada = of(year, month + 1, dayOfMonth)
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
                ).show()
            }) {
                Text(text = fechaSeleccionada?.toString() ?: "Seleccionar Fecha")
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (fechaSeleccionada != null) {
                Text("Horas disponibles:", style = MaterialTheme.typography.titleMedium)
                disponibilidades.filter { it.fecha == fechaSeleccionada }.forEach { disponibilidad ->
                    Button(
                        onClick = { horaSeleccionada = disponibilidad.horaInicio },
                        modifier = Modifier
                            .padding(4.dp)
                            .fillMaxWidth()
                    ) {
                        Text(disponibilidad.horaInicio.toString())
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (psicologoSeleccionado != null && fechaSeleccionada != null && horaSeleccionada != null) {
                        val cita = CitaRequest(
                            pacienteId = 1L, // Reemplazar con ID real del usuario
                            psicologoId = psicologoSeleccionado!!.id,
                            fecha = fechaSeleccionada!!,
                            hora = horaSeleccionada!!
                        )
                        viewModel.agendarCita(cita) {
                            navController.navigate("detalle_cita/${it.id}")
                        }
                    } else {
                        Toast.makeText(context, "Completa todos los campos", Toast.LENGTH_SHORT).show()
                    }
                },
                enabled = psicologoSeleccionado != null && fechaSeleccionada != null && horaSeleccionada != null
            ) {
                Text("Confirmar Cita")
            }

            citaAgendada?.let { cita ->
                Spacer(modifier = Modifier.height(24.dp))
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("✅ Cita Agendada", style = MaterialTheme.typography.titleLarge)
                        Text("📅 Fecha: ${cita.fecha}")
                        Text("⏰ Hora: ${cita.hora}")
                        Text("🧠 Psicólogo: ${psicologoSeleccionado?.nombre ?: "Desconocido"}")
                    }
                }
            }
        }
    }
}

@Composable
fun DropdownMenuPsicologos(
    psicologos: List<PsicologoResponse>,
    seleccionado: PsicologoResponse?,
    onSeleccionar: (PsicologoResponse?) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    var selectedNombre by remember { mutableStateOf(seleccionado?.nombre ?: "Seleccionar Psicólogo") }

    Box {
        Button(onClick = { expanded = true }) {
            Text(text = selectedNombre)
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            psicologos.forEach { psicologo ->
                DropdownMenuItem(
                    text = { Text(text = psicologo.nombre) },
                    onClick = {
                        selectedNombre = psicologo.nombre
                        onSeleccionar(psicologo)
                        expanded = false
                    }
                )
            }
        }
    }
}
