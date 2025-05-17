import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    val frases = listOf(
        "La salud mental es tan importante como la física.",
        "Cuidar tu mente es cuidarte a ti mismo.",
        "Hablar es el primer paso para sanar.",
        "Eres más fuerte de lo que crees.",
        "Un día a la vez, un paso a la vez."
    )
    var index by remember { mutableStateOf(0) }

    // Cambiar frase cada 5 segundos
    LaunchedEffect(Unit) {
        while(true) {
            kotlinx.coroutines.delay(5000)
            index = (index + 1) % frases.size
        }
    }

    // Contenedor Box principal que ocupará toda la pantalla
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD))
        // contentAlignment = Alignment.Center // Puedes quitarlo si alineas cada hijo individualmente
    ) {
        // Texto con frases, alineado al centro del Box
        Text(
            text = frases[index],
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .align(Alignment.Center) // Alinear este Text específicamente al centro del Box
                .padding(24.dp),
            textAlign = TextAlign.Center,
            color = Color(0xFF0D47A1)
        )

        // Columna con los botones, ahora SÍ está DENTRO del Box
        // y por lo tanto puede usar Modifier.align()
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .align(Alignment.BottomCenter), // Esto ahora es válido
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { navController.navigate("login") },
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text("Iniciar sesión")
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                onClick = { navController.navigate("registro") },
                modifier = Modifier.fillMaxWidth(0.8f)
            ) {
                Text("Registrarse")
            }
        }
    }
}