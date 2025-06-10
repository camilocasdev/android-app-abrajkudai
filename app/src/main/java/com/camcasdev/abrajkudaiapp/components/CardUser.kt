package com.camcasdev.abrajkudaiapp.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.camcasdev.abrajkudaiapp.models.User

@Composable
fun CardUser(
    user: User,
    fun_id: (String) -> Unit,
    funNombre: (String) -> Unit,
    funApellido: (String) -> Unit,
    funPais: (String) -> Unit,
    funIdentificacion: (String) -> Unit,
    funContrasena: (String) -> Unit,
    funCorreo: (String) -> Unit,
    funTelefono: (String) -> Unit,
    funRole: (String) -> Unit,

    funIsEditing: (Boolean) -> Unit,
    funTextButton: (String) -> Unit,
    funDeleteUser: (String) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            Arrangement.Center
        ) {
            Text(
                text = "Nombre: ${user.nombre}",
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Apellido: ${user.apellido}",
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "Correo: ${user.correo}",
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "País: ${user.pais}",
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Row(
                modifier = Modifier.fillMaxSize()
            ) {
                Button(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .weight(1f),
                    onClick = {
                        fun_id(user._id ?: "")
                        funNombre(user.nombre ?: "")
                        funApellido(user.apellido ?: "")
                        funPais(user.pais ?: "")
                        funIdentificacion(user.identificacion ?: "")
                        funContrasena("")
                        funCorreo(user.correo ?: "")
                        funTelefono((user.telefono ?: "").toString())
                        funRole(user.role?.get(0) ?: "")
                        funIsEditing(true)
                        funTextButton("Actualizar Usuario")
                    }
                ) {
                    Text(
                        text = "Editar"
                    )
                }
                Button(
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .weight(1f),
                    onClick = { user._id?.let { funDeleteUser(it) } }
                ) {
                    Text(
                        text = "Borrar"
                    )
                }
            }
        }
    }
}
