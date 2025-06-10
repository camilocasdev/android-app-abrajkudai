package com.camcasdev.abrajkudaiapp.models

import androidx.annotation.Keep

@Keep
data class User(
    val _id: String?,
    val nombre: String?,
    val apellido: String?,
    val pais: String?,
    val identificacion: String?,
    val contrasena: String?,
    val correo: String?,
    val telefono: Long?,
    val role: List<String>?,

    val codigo: Int? = null,
    val createdAt: String? = null,
    val updatedAt: String? = null
)
