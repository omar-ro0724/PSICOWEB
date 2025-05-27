package com.example.psicowebfront

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.psicowebfront.Navigation.Navigation
import com.example.psicowebfront.ui.theme.PsicowebTheme
import com.example.psicowebfront.viewModel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PsicowebTheme {
                val navController = rememberNavController()
                val loginViewModel: LoginViewModel = hiltViewModel()
                Surface(color = MaterialTheme.colorScheme.background) {
                    Navigation(navController, loginViewModel)
                }
            }
        }
    }
}
