fun main(){
    println("Introduce un numero: ")
    val n = readLine()!!.toInt()
    println(if (esPrimo(n)) "es primo" else "no es primo")
}

fun esPrimo(x: Int): Boolean{
    var divisores = 0;

    for (i in 1..x){
        if (x%i == 0){
            divisores ++
        }
    }
    return if (divisores == 2) true else false
}
