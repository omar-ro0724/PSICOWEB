import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.psicowebfront.Modelo.LoginRequest
import com.example.psicowebfront.Modelo.Usuario
import com.example.psicowebfront.Network.RetrofitCliente
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _usuarioActual = MutableStateFlow<Usuario?>(null)
    val usuarioActual: StateFlow<Usuario?> = _usuarioActual

    private val _errorLogin = MutableStateFlow<String?>(null)
    val errorLogin: StateFlow<String?> = _errorLogin

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun login(email: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val loginRequest = LoginRequest(correo = email, contrasena = password)
                val response = RetrofitCliente.instance.login(loginRequest)

                if (response.isSuccessful) {
                    val usuario = response.body()
                    if (usuario != null) {
                        _usuarioActual.value = usuario
                        _errorLogin.value = null
                    } else {
                        _usuarioActual.value = null
                        _errorLogin.value = "Usuario no encontrado"
                    }
                } else {
                    _usuarioActual.value = null
                    _errorLogin.value = "Credenciales incorrectas"
                }
            } catch (e: Exception) {
                _usuarioActual.value = null
                _errorLogin.value = "Error al iniciar sesión: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun limpiarError() {
        _errorLogin.value = null
    }

    fun logout() {
        _usuarioActual.value = null
    }
}