package com.nachocabanes.firestore2021

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btMostrar : Button = findViewById(R.id.btMostrar)
        val tvResultados : TextView = findViewById(R.id.tvResultados)
        val db : FirebaseFirestore = FirebaseFirestore.getInstance()
        var respuesta = ""

        btMostrar.setOnClickListener {
            //respuesta = ""

            db.collection("mascotas").get().
            addOnSuccessListener { resultado ->
                for (documento in resultado)
                    respuesta += documento["nombre"].toString() + "\n"
            }
            .addOnFailureListener {
                respuesta = "Datos no encontrados"
            }
            tvResultados.text = respuesta

        }
    }
}