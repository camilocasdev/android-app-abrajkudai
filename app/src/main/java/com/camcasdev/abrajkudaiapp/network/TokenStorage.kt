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

    private const val TOKEN_BASE = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjY4MmQ1ZDFhODE0ZWQ3MGFlMmRmN2FjYSIsInJlZnJlc2giOnRydWUsImlhdCI6MTc0OTM1NDQ1MSwiZXhwIjoxNzUzMjQyNDUxfQ.pQx_cz7ESSrbbZjY4D6vtzQCE5zxruLK660EUhUnlRk"

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

    fun clear() {
        sharedPreferences.edit { remove(AUTH_ACCESS_TOKEN).remove(AUTH_TOOKIE).apply() }
    }
}