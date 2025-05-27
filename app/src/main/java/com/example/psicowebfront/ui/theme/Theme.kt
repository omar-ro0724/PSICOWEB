package com.example.psicowebfront.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColors = lightColorScheme(
    primary = Color(0xFF1976D2),
    onPrimary = Color.White,
    secondary = Color(0xFF388E3C),
    onSecondary = Color.White,
    background = Color(0xFFF5F5F5),
    surface = Color.White,
    onSurface = Color(0xFF222222),
    error = Color(0xFFD32F2F),
    onError = Color.White
)

@Composable
fun PsicowebTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = LightColors,
        typography = Typography(
            headlineLarge = MaterialTheme.typography.headlineLarge.copy(color = Color(0xFF1976D2)),
            bodyLarge = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFF222222)),
            labelLarge = MaterialTheme.typography.labelLarge.copy(color = Color(0xFF1976D2))
        ),
        content = content
    )
}
