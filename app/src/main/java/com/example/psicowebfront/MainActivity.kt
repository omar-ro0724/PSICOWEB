package com.example.psicowebfront

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.psicowebfront.Navigation.Navigation
import com.example.psicowebfront.Screen.LoginViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val loginViewModel: LoginViewModel = viewModel()
            Navigation(navController, loginViewModel)
        }

    }
}