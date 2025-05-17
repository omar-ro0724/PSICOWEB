package com.example.psicowebfront.Screen


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun UsuarioHomeScreen(navController: NavController) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text("Bienvenido, Usuario", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate("psicologos") }, modifier = Modifier.fillMaxWidth()) {
            Text("Ver Psicólogos Disponibles")
        }
    }
}