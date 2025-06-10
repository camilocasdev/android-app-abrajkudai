package com.camcasdev.abrajkudaiapp.network

import android.content.SharedPreferences
import android.content.Context
import androidx.core.content.edit

object TokenStorage {

    /* Por motivos de autenticación en mi API he tenido que implementar esto
    * siempre y cuando no este implementado un sistema de inicio de sesión,
    * esto tambien aplica para el inicio de sesión, la diferencia es que estoy
    * setenado un token de acceso directamente en el header de la petición. */

    private const val SAVE_FILNAME = "tokenauthdoc_do_not_delete"
    private const val AUTH_ACCESS_TOKEN = "accessToken"
    private const val AUTH_TOOKIE = "Tookie"

    private const val TOKEN_BASE = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY4NDc3NWU2MmZmYjhlODFkN2I3NTIwOCIsInJlZnJlc2giOmZhbHNlLCJpYXQiOjE3NDk1MTQwMjYsImV4cCI6MTc1MzQwMjAyNn0._6tw-sUiDLi87kxrxKjpdchrqgVEXhbPEHbNGXnCyxo"

    private lateinit var sharedPreferences: SharedPreferences

    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(SAVE_FILNAME, Context.MODE_PRIVATE)
    }

    var accessToken: String?
        get() = sharedPreferences.getString(AUTH_ACCESS_TOKEN, TOKEN_BASE)
        set(value){
            sharedPreferences.edit { putString(AUTH_ACCESS_TOKEN, value).apply() }
        }

    var tookie: String?
        get() = sharedPreferences.getString(AUTH_TOOKIE, null)
        set(value){
            sharedPreferences.edit { putString(AUTH_TOOKIE, value).apply() }
        }

    fun clear() {  //Pensado para cuando se cierre sesión ( NO EXISTE PARA LA EVIDENCIA)
        sharedPreferences.edit { remove(AUTH_ACCESS_TOKEN).remove(AUTH_TOOKIE).apply() }
    }
}