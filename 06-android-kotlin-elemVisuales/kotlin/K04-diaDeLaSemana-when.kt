fun main() {
    println("Introduce un número de día de la semana: ")
    when (readLine()!!.toInt()) {
        1 -> println("Lunes")
        2 -> println("Martes")
        3 -> println("Miercoles")
        4 -> println("Jueves")
        5 -> println("Viernes")
        6, 7 -> println("Fin de semana")
        else -> {
            println("Numero no valido")
        }
    }
}
