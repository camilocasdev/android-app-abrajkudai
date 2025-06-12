package com.camcasdev.abrajkudaiapp.viewmodels

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.camcasdev.abrajkudaiapp.models.User
import com.camcasdev.abrajkudaiapp.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.coroutines.withTimeout
import kotlinx.coroutines.delay
import retrofit2.HttpException
import java.io.IOException

class UserViewModel : ViewModel() {

    var _userList: List<User> by mutableStateOf(arrayListOf())

    fun createUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.apiService.createUser(user)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    getUserList()
                }
            }
        }
    }

    fun getUserList() {
        viewModelScope.launch(Dispatchers.IO) {
            var attempt = 0
            var success = false
            while ( attempt <= 5 && !success) {
                try {
                    val response = RetrofitClient.apiService.getUserList()

                    if (response.isSuccessful) {
                        Log.d("CC-UserViewModel", "La conexión a la API ha sido exitosa, la información se ha obtenido en el intento: $attempt")
                        _userList = response.body() ?: emptyList()
                        success = true
                    } else {
                        Log.e("API", "Respuesta no exitosa: ${response.code()}")
                        throw IOException()
                    }
                } catch (e: IOException) {
                    Log.e("CC-UserViewModel", "Error al conectar con la API, reintentando... (Intento actual: $attempt)", e)
                    attempt++
                    if (attempt <= 5) {
                        delay(20_000)
                    }
                } catch (e: Exception) {
                    Log.e("API", "Error inesperado en intento #${attempt + 1}", e)
                    attempt = 0
                    break
                }
            }
        }
    }

    fun getUserById(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.apiService.getUserById(userId)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _userList = listOf(response.body()!!)
                }
            }
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.apiService.updateUser(user._id!!, user)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    getUserList()
                }
            }
        }
    }

    fun deleteUser(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = RetrofitClient.apiService.deleteUser(userId)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    getUserList()
                }
            }
        }
    }
}
