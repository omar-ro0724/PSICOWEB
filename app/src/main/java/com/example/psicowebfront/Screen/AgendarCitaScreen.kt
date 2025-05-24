package com.example.psicowebfront.Screen

import android.app.DatePickerDialog
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.psicowebfront.Modelo.CitaRequest
import com.example.psicowebfront.Modelo.PsicologoResponse
import com.example.psicowebfront.viewModel.CitaViewModel
import java.time.LocalDate
import java.time.LocalTime
import java.util.*

@Composable
fun AgendarCitaScreen(viewModel: CitaViewModel = hiltViewModel()) {
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

            DropdownMenuPsicologos(psicologos, psicologoSeleccionado) { seleccionado ->
                psicologoSeleccionado = seleccionado
                seleccionado?.let {
                    viewModel.obtenerDisponibilidades(it.id)
                }
                fechaSeleccionada = null
                horaSeleccionada = null
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = {
                val calendar = Calendar.getInstance()
                DatePickerDialog(
                    context,
                    { _, year, month, dayOfMonth ->
                        fechaSeleccionada = LocalDate.of(year, month + 1, dayOfMonth)
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
                            pacienteId = viewModel.usuarioId,
                            psicologoId = psicologoSeleccionado!!.id,
                            fecha = fechaSeleccionada!!,
                            hora = horaSeleccionada!!
                        )
                        viewModel.agendarCita(cita)
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
                        Text("✅ Recibo de Cita", style = MaterialTheme.typography.titleLarge)
                        Text("📅 Fecha: ${cita.fecha}")
                        Text("⏰ Hora: ${cita.hora}")
                        Text("🧠 Psicólogo: ${psicologoSeleccionado?.nombre ?: "Desconocido"}")
                        Text("🙍 Paciente ID: ${cita.usuarioId}")
                        Text("💰 Valor: \$60.000 COP")
                        Text("🔖 Referencia: ${cita.referencia ?: "Generando..."}")
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
        DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
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
