fun main() {
    println("Dime un numero")
    val n = readLine()!!.toInt()
    for (i in 0..10){
        println("$n x $i = ${n*i}");
    }
}
