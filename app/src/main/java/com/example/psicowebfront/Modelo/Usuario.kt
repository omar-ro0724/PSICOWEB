package com.example.psicowebfront.Modelo

data class Usuario(
    val id: Int = 0,
    val nombre: String,
    val apellido: String,
    val correo: String,
    val contrasena: String,
    val rol: String,
    )