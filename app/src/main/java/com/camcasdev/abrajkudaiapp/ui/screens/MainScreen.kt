package com.camcasdev.abrajkudaiapp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.camcasdev.abrajkudaiapp.components.Formulario
import com.camcasdev.abrajkudaiapp.models.User
import com.camcasdev.abrajkudaiapp.viewmodels.UserViewModel
import com.camcasdev.abrajkudaiapp.components.CardUser

@Composable
fun MainScreen(viewModel: UserViewModel) {

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        viewModel.getUserList()
        ScreenCRUD(viewModel._userList, viewModel)
    }
}

@Composable
fun ScreenCRUD(userList: List<User>, viewModel: UserViewModel) {

    var _id by remember { mutableStateOf("") }
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var pais by remember { mutableStateOf("") }
    var identificacion by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("") }
    var correo by remember { mutableStateOf("") }
    var telefono by remember { mutableStateOf("") }
    var role by remember { mutableStateOf("") }
    var isEditing by remember { mutableStateOf(false) }
    var textButton by remember { mutableStateOf("Crear Usuario") }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(12.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .statusBarsPadding()
            .padding(
                top = 0.dp,
                start = 15.dp,
                end = 15.dp,
                bottom = 0.dp
            )
    ) {
        Formulario(
            _id = _id,
            nombre = nombre,
            funNombre = { nombre = it },
            apellido = apellido,
            funApellido = { apellido = it },
            pais = pais,
            funPais = { pais = it },
            identificacion = identificacion,
            funIdentificacion = { identificacion = it },
            contrasena = contrasena,
            funContrasena = { contrasena = it },
            correo = correo,
            funCorreo = { correo = it },
            telefono = telefono,
            funTelefono = { telefono = it },
            role = role,
            funRole = { role = it },
            isEditing = isEditing,
            funIsEditing = { isEditing = false },
            textButton = textButton,
            funTextButton = { textButton = it },
            funResetCampos = {
                nombre = ""
                apellido = ""
                pais = ""
                identificacion = ""
                contrasena = ""
                correo = ""
                telefono = ""
                role = ""
            },
            viewModel = viewModel
        )
        Column (
            modifier = Modifier
                .fillMaxWidth()
        ) {
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
            ) {
                items(userList) { user ->
                    CardUser(
                        user = user,
                        fun_id = { _id = it },
                        funNombre = { nombre = it },
                        funApellido = { apellido = it },
                        funPais = { pais = it },
                        funIdentificacion = { identificacion = it },
                        funContrasena = { contrasena = it },
                        funCorreo = { correo = it },
                        funTelefono = { telefono = it },
                        funRole = { role = it },
                        funTextButton = { textButton = it },
                        funIsEditing = { isEditing = it },
                        funDeleteUser = {
                            viewModel.deleteUser(user._id!!)
                        }
                    )
                }
            }
        }
    }
}

