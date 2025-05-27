package com.example.psicowebfront.Navigation

import HomeScreen
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.psicowebfront.Screen.AdminHomeScreen
import com.example.psicowebfront.Screen.AgendarCitaScreen
import com.example.psicowebfront.Screen.AgregarPsicologoScreen
import com.example.psicowebfront.Screen.GestionarPermisosScreen
import com.example.psicowebfront.Screen.LoginScreen
import com.example.psicowebfront.Screen.PsicologoHomeScreen
import com.example.psicowebfront.Screen.RegistroScreen
import com.example.psicowebfront.Screen.UsuarioHomeScreen
import com.example.psicowebfront.Screen.VerUsuariosScreen
import com.example.psicowebfront.viewModel.AgendarCitaViewModel
import com.example.psicowebfront.viewModel.AuthViewModel
import com.example.psicowebfront.viewModel.LoginViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation(navController: NavHostController, loginViewModel: LoginViewModel) {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = hiltViewModel()

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
            arguments = listOf(navArgument("psicologoId") {
                type = NavType.LongType
            })
        ) { backStackEntry ->
            val viewModel: AgendarCitaViewModel = hiltViewModel()
            AgendarCitaScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
    }
}
