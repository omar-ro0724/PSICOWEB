package com.example.psicowebfront.Navigation

import HomeScreen
import LoginViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.psicowebfront.Modelo.Usuario
import com.example.psicowebfront.Screen.*
import kotlinx.coroutines.launch

@Composable
fun Navigation(navController1: NavHostController, loginViewModel: LoginViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "bienvenida") {

        // Pantallas públicas
        composable("bienvenida") { HomeScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("registro") { RegistroScreen(navController) }

        // Pantallas según rol
        composable("usuario_home") { UsuarioHomeScreen(navController) }
        composable("psicologo_home") { PsicologoHomeScreen(navController) }
        composable("admin_home") { AdminHomeScreen(navController) }

        // Funcionalidades por rol
        composable("agenda_psicologo") { PsicologoHomeScreen(navController) }
        composable("ver_usuarios") { VerUsuariosScreen() }
        composable("agregar_psicologo") { AgregarPsicologoScreen(navController) }
        composable("gestionar_permisos") { GestionarPermisosScreen() }

        composable(
            route = "agendar/{psicologoId}",
            arguments = listOf(navArgument("psicologoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val psicologoId = backStackEntry.arguments?.getInt("psicologoId") ?: 0
            AgendarCitaScreen(navController, psicologoId)
        }
    }
}
