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
    val codigo: Int?,

    val createdAt: String?,
    val updatedAt: String?
)
