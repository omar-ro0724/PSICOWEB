package com.example.psicowebfront.Navigation

import HomeScreen
import LoginViewModel
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.*
import androidx.navigation.navArgument
import com.example.psicowebfront.Screen.*
import com.example.psicowebfront.Screen.AgendarCitaScreen
import com.example.psicowebfront.viewModel.CitaViewModel

@Composable
fun Navigation(navController1: NavHostController, loginViewModel: LoginViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "bienvenida") {

        composable("bienvenida") { HomeScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("registro") { RegistroScreen(navController) }

        composable("usuario_home") { UsuarioHomeScreen(navController) }
        composable("psicologo_home") { PsicologoHomeScreen(navController) }
        composable("admin_home") { AdminHomeScreen(navController) }

        composable("agenda_psicologo") { PsicologoHomeScreen(navController) }
        composable("ver_usuarios") { VerUsuariosScreen() }
        composable("agregar_psicologo") { AgregarPsicologoScreen(navController) }
        composable("gestionar_permisos") { GestionarPermisosScreen() }

        composable(
            route = "agendar/{psicologoId}",
            arguments = listOf(navArgument("psicologoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val psicologoId = backStackEntry.arguments?.getInt("psicologoId") ?: 0
            val citaViewModel: CitaViewModel = viewModel()
            AgendarCitaScreen(viewModel = citaViewModel)


        }
    }
}
