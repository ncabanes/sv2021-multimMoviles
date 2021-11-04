fun main() {
    println("Dime un número" )
    val n1 = readLine()!!.toInt()
    println("Dime otro número" )
    val n2 = readLine()!!.toInt()
    println("El resto es ${n1%n2}")
}
