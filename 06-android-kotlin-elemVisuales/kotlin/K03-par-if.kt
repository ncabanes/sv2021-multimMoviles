fun main() {
    
    print("Introduce un numero entero: ");
    var n=readLine()!!.toInt();
    var par=if(n%2==0) "par" else "impar";
    println("El numero es $par");
}
