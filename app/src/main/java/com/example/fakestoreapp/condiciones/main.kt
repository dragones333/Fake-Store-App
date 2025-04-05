package com.example.fakestoreapp.condiciones

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main(){
    //Launch
    //Scope
    //SOLO PARA PROBAR
    //runBlocking
    print("||cortinas||")
    cAsync()
    }


fun cLaunch(){
    runBlocking {
    //Hacer una consulta a una base de datos
    println("Cargando interfaz grafica")
    launch {
        consultaDB()
    }
    println("Continuo cargando interfaz grafica")
    println("Interfaz cargada")
}
    }
fun cAsync(){
    //solo para pruebas
    runBlocking {
        println("Cargando interfaz grafica")
        val result=async {
            println("Haciendo Get a Api de fakestore")
            delay(2000)
            "Datos de la API en Json {id:Hola}"
        }
        val resultString=result
        println("El resultado es ${resultString}")
    }
}
suspend fun consultaDB(){
    println("Consulta a base de datos")
    delay(3000)
    println("Termina consulta de base de datos")
}
