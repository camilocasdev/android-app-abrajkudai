package com.camcasdev.abrajkudaiapp.network

import okhttp3.Interceptor
import okhttp3.Response

class ApiInterceptor : Interceptor  {

    //AQUÍ DECLARAMOS HEADERS PARA ENVIAR DIRECTAMENTE (COMO TOKENS DE AUTH)

    override fun intercept(chain: Interceptor.Chain): Response {

        val tokenAcceso = TokenStorage.accessToken
        val tookie = TokenStorage.tookie

        val cookieValue = if (tookie == null) {
            "accessToken=$tokenAcceso"
        } else {
            "accessToken=$tokenAcceso; Tookie=$tookie"
        }

        println("Enviando cabecera Cookie: $cookieValue")

        val request = chain.request()
            .newBuilder()
            .addHeader("Cookie", cookieValue)
            .build()

        println("Enviando cabecera Cookie: Tookie=$tokenAcceso")
        return chain.proceed(request)

    }
}