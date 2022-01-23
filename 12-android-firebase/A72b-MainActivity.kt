package com.nachocabanes.firestore2021

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btMostrar : Button = findViewById(R.id.btMostrar)
        val btGuardar : Button = findViewById(R.id.btGuardar)
        val btModificar : Button = findViewById(R.id.btModificar)
        val btBorrar : Button = findViewById(R.id.btBorrar)
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
                for (documento in resultado) {
                    respuesta +=
                        documento.id.toString() + " - " +
                                documento["nombre"].toString() + " (" +
                                documento["raza"].toString() + ")\n"
                }
            }
            .addOnFailureListener {
                respuesta = "Datos no encontrados"
            }
            tvResultados.text = respuesta
        }

        btGuardar.setOnClickListener {
            if (etId.text.toString().isNotBlank()
                    && etNombre.text.toString().isNotBlank()
                    && etRaza.text.toString().isNotBlank()) {
                val mascota = hashMapOf(
                    "nombre" to etNombre.text.toString(),
                    "raza" to etRaza.text.toString()
                )
                db.collection("mascotas").document(etId.text.toString())
                    .set(mascota)
                    .addOnSuccessListener { _ ->
                        tvResultados.text = "Añadido"
                    }
                    .addOnFailureListener { _ ->
                        tvResultados.text = "No se ha podido añadir"
                    }
            }
        }

        btModificar.setOnClickListener {
            var mascota = hashMapOf<String,String>()
            var cambiado = false
            if (etNombre.text.toString().isNotBlank()) {
                mascota["nombre"] = etNombre.text.toString()
                cambiado = true
            }
            if (etRaza.text.toString().isNotBlank()) {
                mascota["raza"] = etRaza.text.toString()
                cambiado = true
            }

            if (cambiado) {
                db.collection("mascotas").document(etId.text.toString())
                    .update(mascota.toMap())
                    .addOnSuccessListener { _ ->
                        tvResultados.text = "Modificado"
                    }
                    .addOnFailureListener { _ ->
                        tvResultados.text = "No se ha podido modificar"
                    }
            }
        }

        btBorrar.setOnClickListener {
            if (etId.text.toString().isNotBlank()) {
                db.collection("mascotas").document(etId.text.toString())
                    .delete()
                    .addOnSuccessListener { _ ->
                        tvResultados.text = "Borrado"
                    }
                    .addOnFailureListener { _ ->
                        tvResultados.text = "No se ha podido borrar"
                    }
            }
        }
    }
}