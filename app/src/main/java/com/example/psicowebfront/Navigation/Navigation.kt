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
        composable("ver_usuarios") { AdminGestionUsuariosScreen(navController) }
        composable("agregar_psicologo") { AdminGestionUsuariosScreen(navController) }
        composable("gestionar_permisos") { AdminGestionUsuariosScreen(navController) }

        composable(
            route = "agendar/{psicologoId}",
            arguments = listOf(navArgument("psicologoId") { type = NavType.IntType })
        ) { backStackEntry ->
            val psicologoId = backStackEntry.arguments?.getInt("psicologoId") ?: 0
            AgendarCitaScreen(navController, psicologoId)
        }
    }
}

@Composable
fun AdminGestionUsuariosScreen(x0: NavHostController) {
    TODO("Not yet implemented")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScaffold(
    usuario: Usuario,
    onLogout: () -> Unit,
    navController: NavHostController,
    content: @Composable () -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val menuItems = when (usuario.rol.lowercase()) {
        "usuario" -> listOf("Inicio" to "usuario_home", "Ver Psicólogos" to "psicologo_home")
        "psicologo" -> listOf("Inicio" to "psicologo_home", "Mi Agenda" to "agenda_psicologo")
        "admin" -> listOf(
            "Inicio" to "admin_home",
            "Usuarios" to "ver_usuarios",
            "Psicólogos" to "agregar_psicologo",
            "Gestionar Roles" to "gestionar_permisos"
        )
        else -> emptyList()
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Column(modifier = Modifier.fillMaxSize()) {
                Text(
                    text = "Bienvenido, ${usuario.nombre}",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(16.dp)
                )

                Divider()

                menuItems.forEach { (label, route) ->
                    Text(
                        text = label,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(route)
                                scope.launch { drawerState.close() }
                            }
                            .padding(16.dp)
                    )
                }

                Spacer(modifier = Modifier.weight(1f))

                Divider()
                Text(
                    text = "Cerrar sesión",
                    color = Color.Red,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onLogout()
                            scope.launch { drawerState.close() }
                        }
                        .padding(16.dp)
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("Psicoweb") },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = "Menu")

                        }
                    }
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                content()
            }
        }
    }
}
