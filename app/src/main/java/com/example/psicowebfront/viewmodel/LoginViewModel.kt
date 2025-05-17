import com.example.psicowebfront.Modelo.LoginRequest
import com.example.psicowebfront.Modelo.Usuario
import com.example.psicowebfront.Network.RetrofitCliente
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

class LoginViewModel : ViewModel() {

    private val _usuarioActual = MutableStateFlow<Usuario?>(null)
    val usuarioActual = _usuarioActual.asStateFlow()

    private val _errorLogin = MutableStateFlow<String?>(null)
    val errorLogin = _errorLogin.asStateFlow()

    fun login(email: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            try {
                // ✅ Crear LoginRequest correctamente
                val loginRequest = LoginRequest(correo = email, contrasena = password)

                // ✅ Llamar al método Retrofit correctamente
                val response = RetrofitCliente.instance.login(loginRequest)

                if (response.isSuccessful) {
                    // ✅ Obtener usuario desde body
                    _usuarioActual.value = response.body()
                    _errorLogin.value = null
                    onResult(true)
                } else {
                    _usuarioActual.value = null
                    _errorLogin.value = "Credenciales incorrectas"
                    onResult(false)
                }
            } catch (e: Exception) {
                _usuarioActual.value = null
                _errorLogin.value = "Error al iniciar sesión: ${e.message}"
                onResult(false)
            }
        }
    }
}
