package com.nity.espalindromo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvRespuesta = findViewById<TextView>(R.id.tvRespuesta)
        val btPrimo = findViewById<Button>(R.id.btBuscarPalindromo)
        val txNumero = findViewById<TextView>(R.id.txNumero)

        btPrimo.setOnClickListener {
            try {
                tvRespuesta.text =
                    if (esPalindromo(txNumero.text.toString().toInt())) "Es palindromo"
                    else "No es palindromo"
            }
            catch (e : Exception) {
                Toast.makeText(applicationContext,
                    "Número no válido.",
                    Toast.LENGTH_LONG).show()
            }
        }

    }

    fun esPalindromo(n :Int): Boolean {

        val nLocal :String = n.toString()
        if(nLocal.length % 2 == 0)
            return nLocal.subSequence(0, nLocal.length / 2).equals(nLocal.subSequence(nLocal.length / 2, nLocal.length))
        else
            return nLocal.subSequence(0, nLocal.length / 2).equals(nLocal.subSequence(nLocal.length / 2 + 1, nLocal.length))
    }
}