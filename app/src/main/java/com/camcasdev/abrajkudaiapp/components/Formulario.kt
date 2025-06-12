package com.camcasdev.abrajkudaiapp.components

import android.util.Log
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
            var esFormularioValido = true
            var telefonoLong: Long?

            if (nombre.isBlank()) {
                esFormularioValido = false
            }

            if (telefono.isBlank()) {
                telefonoLong = null
            } else {
                try {
                    telefonoLong = telefono.toLong()
                } catch (e: NumberFormatException) {
                    telefonoLong = null
                    esFormularioValido = false
                    Log.e("Formulario", "Error al convertir teléfono: '$telefono'", e)
                }
            }

            if (!esFormularioValido) {
                Log.d("Formulario", "Validación fallida. No se llamará al ViewModel.")
                return@Button
            }

            val usuario = User(
                _id = if (isEditing) _id else null,
                nombre = nombre,
                apellido = apellido,
                pais = pais,
                identificacion = identificacion,
                contrasena = if (contrasena.isNotBlank()) contrasena else null,
                correo = correo,
                telefono = telefonoLong,
                role = if (role.isNotBlank()) listOf(role) else emptyList()
            )

            if (isEditing) {
                viewModel.updateUser(usuario)
                funTextButton("Agregar Usuario")
                funIsEditing()
            } else {
                viewModel.createUser(usuario)
            }
            funResetCampos()
        }
    ) {
        Text(
            text = textButton
        )
    }
}
