package com.example.fakestoreapp

// Lambdas. Que son? Funciones anonimas o sin nombre.
// Sirven para hacer callbacks

// Que es un callback? Mandar una funcion como parametro de otra funcion

fun main(){
    val operacionSumar = { a:Int,b:Int -> a + b  }
    val operacionResta = { a:Int,b:Int -> a - b }

    val sumaDeNumeros = operacionSumar(2,3)
    println(sumaDeNumeros)
    saludar("Juan")

    println("===== CALLBACKS =====")
    val resultado = operar(2,10,operacionResta)
    println(resultado)

    println("===== FUNCIONES DE LISTA ====")
    val lista = listOf(1,2,3,4,5)
    val numerosPares = lista.filter { it % 2 == 0 }
    println(numerosPares)

    println("==== FUNCION OBTENER OPERADOR")
    val obtenerOperador = getFunction("dividir")
    println(obtenerOperador(12,4))

    /*
    Crea una funcion que se llame procesarArchivo.
    Que simule una tarea (Como leer un archivo) y que reciba
    un callback que se llame terminar. Print. Recibir un string
    */
    println("==== Procesar Archivo ====")
    //val terminarDeLeer={nombre:String-> println("Terminando de leer el Archivo:$nombre")}
    procesarArchivo("datos.txt"){nombre->
        println("Terminando de copiar el archivo a otra carpeta $nombre")
    }
}
fun procesarArchivo(nombre: String, terminar:(String)->Unit) {
    println("Leyendo archivo con nombre: $nombre")
    terminar(nombre)
}

val saludar = { nombre:String -> println("Hola, $nombre") }


fun sumar(a:Int,b:Int):Int{
    return a + b
}

fun operar(a:Int,b:Int,operacion:(Int,Int)->Int) : Int{
    return operacion(a,b)
}


fun getFunction(tipo:String) : (Int,Int) -> Int {
    return when(tipo){
        "suma" -> { a : Int, b: Int -> a + b }
        "resta" -> { a : Int, b : Int -> a - b }
        "multiplicar" -> { a : Int, b : Int -> a * b }
        "dividir" -> { a : Int, b : Int -> a / b }
        else -> { _, _ -> 0 }
    }
}

