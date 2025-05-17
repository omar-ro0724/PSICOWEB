package com.example.psicowebfront.Screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun SeleccionRolScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD))
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Bienvenido a Psicoweb",
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1976D2)
        )

        Spacer(modifier = Modifier.height(32.dp))

        RolButton("Soy Usuario") {
            navController.navigate("login/usuario")
        }

        RolButton("Soy Psicólogo") {
            navController.navigate("login/psicologo")
        }

        RolButton("Soy Administrador") {
            navController.navigate("login/admin")
        }
    }
}

@Composable
fun RolButton(texto: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1976D2))
    ) {
        Text(text = texto, color = Color.White, fontSize = 18.sp)
    }
}
