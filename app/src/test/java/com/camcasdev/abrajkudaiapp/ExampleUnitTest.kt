package com.camcasdev.abrajkudaiapp

import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.Test

import org.junit.Assert.*
import kotlinx.coroutines.delay

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class ExampleUnitTest {
    @Test
    fun main_test_runner() {
        println("First Test, calling suspended fun bucle_test()")
        val test = runBlocking {
            bucle_test()
        }
    }
companion object
}

private suspend fun ExampleUnitTest.Companion.bucle_test() {
    var attempt: Int = 0
    var response: Boolean = false

    if (response) {
        println("La respuesta es true desde el inicio")
    } else {
        do {
            withTimeout(20_000) {
                delay(19_000)
            }
            if (!response && attempt == 4) {
                response = true
                println("La respuesta esta llena o completa, o llego en el intento $attempt saliendo...")
            } else {
                println("La respuesta es false, reintentando $attempt")
            }
            attempt++
        } while (!response && attempt <= 5)
    }
}
