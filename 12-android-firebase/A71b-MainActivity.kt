package com.nachocabanes.firestore2021

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btMostrar : Button = findViewById(R.id.btMostrar)
        val btGuardar : Button = findViewById(R.id.btGuardar)
        val tvResultados : TextView = findViewById(R.id.tvResultados)
        val etId : EditText = findViewById(R.id.etId)
        val etNombre : EditText = findViewById(R.id.etNombre)
        val etRaza : EditText = findViewById(R.id.etRaza)

        val db : FirebaseFirestore = FirebaseFirestore.getInstance()
        var respuesta = ""

        btMostrar.setOnClickListener {

            db.collection("mascotas").get().
            addOnSuccessListener { resultado ->
                respuesta = ""
                for (documento in resultado)
                    respuesta += documento["nombre"].toString() + "\n"
            }
            .addOnFailureListener {
                respuesta = "Datos no encontrados"
            }
            tvResultados.text = respuesta

        }

        btGuardar.setOnClickListener {
            val mascota = hashMapOf(
                "nombre" to etNombre.text.toString(),
                "raza" to etRaza.text.toString()
            )

            db.collection("mascotas").document(etId.text.toString())
                .set(mascota)
                .addOnSuccessListener { _ ->
                    tvResultados.text = "Añadido" }
                .addOnFailureListener{ _ ->
                    tvResultados.text = "No se ha podido añadir" }
        }
    }
}