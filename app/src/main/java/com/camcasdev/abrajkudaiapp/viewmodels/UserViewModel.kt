package com.camcasdev.abrajkudaiapp.viewmodels

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
            val response = RetrofitClient.apiService.getUserList()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _userList = response.body() ?: emptyList()
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
