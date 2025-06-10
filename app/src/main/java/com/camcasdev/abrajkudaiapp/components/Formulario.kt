package com.camcasdev.abrajkudaiapp.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.camcasdev.abrajkudaiapp.models.User
import com.camcasdev.abrajkudaiapp.viewmodels.UserViewModel

@Composable
fun Formulario(
    _id: String,
    nombre: String,
    funNombre: (String) -> Unit,
    apellido: String,
    funApellido: (String) -> Unit,
    pais: String,
    funPais: (String) -> Unit,
    identificacion: String,
    funIdentificacion: (String) -> Unit,
    contrasena: String,
    funContrasena: (String) -> Unit,
    correo: String,
    funCorreo: (String) -> Unit,
    telefono: String,
    funTelefono: (String) -> Unit,
    role: String,
    funRole: (String) -> Unit,

    isEditing: Boolean,
    funIsEditing: () -> Unit,
    textButton: String,
    funTextButton: (String) -> Unit,
    funResetCampos: () -> Unit,
    viewModel: UserViewModel
) {
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = nombre,
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = { funNombre(it) },
        label = { Text(text = "Nombre") }
    )
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = apellido,
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = { funApellido(it) },
        label = { Text(text = "Apellido") }
    )

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = pais,
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = { funPais(it) },
        label = { Text(text = "Pais") }
    )

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = identificacion,
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = { funIdentificacion(it) },
        label = { Text(text = "Identificacion") }
    )

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = contrasena,
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        onValueChange = { funContrasena(it) },
        label = { Text(text = "Contrasena") },
        placeholder = { Text("Dejar vacío para no cambiar")}
    )

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = correo,
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
        onValueChange = { funCorreo(it) },
        label = { Text(text = "Correo") }
    )
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = telefono,
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        onValueChange = { funTelefono(it) },
        label = { Text(text = "Telefono") }
    )
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = role,
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        onValueChange = { funRole(it) },
        label = { Text(text = "Role") }
    )
    Spacer(modifier = Modifier.padding(vertical = 10.dp))
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = {
            if (isEditing) {
                viewModel.updateUser(
                    User(
                        _id = _id,
                        nombre = nombre,
                        apellido = apellido,
                        pais = pais,
                        identificacion = identificacion,
                        contrasena = contrasena,
                        correo = correo,
                        telefono = telefono.toLong(),
                        role = listOf(role)
                    )
                )
                funTextButton("Agregar Usuario")
                funIsEditing()
            } else {
                viewModel.createUser(
                    User(
                        _id = _id,
                        nombre = nombre,
                        apellido = apellido,
                        pais = pais,
                        identificacion = identificacion,
                        contrasena = contrasena,
                        correo = correo,
                        telefono = telefono.toLong(),
                        role = listOf(role)
                    )
                )
            }
            funResetCampos()
        }
    ) {
        Text(
            text = textButton
        )
    }
}
