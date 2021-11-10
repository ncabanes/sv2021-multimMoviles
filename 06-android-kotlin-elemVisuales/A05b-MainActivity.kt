package com.nachocabanes.numeroprimo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etNumero : EditText = findViewById(R.id.etNumero)
        val btComprobar : Button = findViewById(R.id.btComprobar)
        val tvRespuesta : TextView = findViewById(R.id.tvRespuesta)

        btComprobar.setOnClickListener {
            try {
                val numero = etNumero.text.toString().toInt()
                val esPrimo : Boolean = esPrimo(numero)
                tvRespuesta.text = if (esPrimo) "$numero es primo"
                    else "$numero no es primo"
                tvRespuesta.visibility = View.VISIBLE
            }
            catch (e : Exception) {
                tvRespuesta.text = "Número no válido"
                tvRespuesta.visibility = View.VISIBLE
            }

        }
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


}