package com.camcasdev.abrajkudaiapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.camcasdev.abrajkudaiapp.models.User
import com.camcasdev.abrajkudaiapp.network.RetrofitClient
import com.camcasdev.abrajkudaiapp.ui.theme.AbrajkudaiappTheme

import com.camcasdev.abrajkudaiapp.network.TokenStorage
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    companion object {
        private var isTokenStorageInitialized = false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        if (!isTokenStorageInitialized) {
            TokenStorage.init(applicationContext)
            isTokenStorageInitialized = true
            Log.d("MainActivity", "TokenStorage inicializado.")
        }

        val serviceInstane = RetrofitClient.apiService //Iniciamos la instancia para petición

        lifecycleScope.launch{
            try{
                Log.d("Corutina", "Iniciando las peticiones")
                val responseData: List<User> = serviceInstane.getUserList()

                Log.d("Lista de Usuarios", "Respuesta de API: $responseData")

            } catch (e: Exception) {
                Log.e("MainActivity", "Error en la petición API", e)
            }
        }

        setContent {
            AbrajkudaiappTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Vamos a ver que pasa",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AbrajkudaiappTheme {
        Greeting("Android")
    }
}