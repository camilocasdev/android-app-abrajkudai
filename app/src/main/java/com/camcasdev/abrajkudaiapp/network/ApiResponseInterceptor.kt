package com.camcasdev.abrajkudaiapp.network

import okhttp3.Interceptor
import okhttp3.Response

private const val AUTH_ACCESS_TOKEN = "accessToken"
private const val AUTH_TOOKIE = "Tookie"

class ApiResponseInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)

        val setCookieHeadersList: List<String> = response.headers("Set-Cookie")

        var newAccessTokenFromResponse: String? = null
        var newTookieFromResponse: String? = null

        if (setCookieHeadersList.isNotEmpty()) {
            for (individualCookieString in setCookieHeadersList) {
                if (individualCookieString.startsWith("$AUTH_ACCESS_TOKEN=", ignoreCase = true)) {
                    newAccessTokenFromResponse = individualCookieString
                        .substringAfter("=")
                        .substringBefore(";")
                        .trim()
                } else if (individualCookieString.startsWith("$AUTH_TOOKIE=", ignoreCase = true)) {
                    newTookieFromResponse = individualCookieString
                        .substringAfter("=")
                        .substringBefore(";")
                        .trim()
                }
            }
        }

        newAccessTokenFromResponse?.takeIf { it.isNotBlank() }?.let {
            println("ResponseInterceptor: Nuevo AccessToken recibido: $it")
            TokenStorage.accessToken = it
        }
        newTookieFromResponse?.takeIf { it.isNotBlank()}?.let {
            println("ResponseInterceptor: Nuevo Tookie recibido: $it")
            TokenStorage.tookie = it
        }

        return response
    }
}