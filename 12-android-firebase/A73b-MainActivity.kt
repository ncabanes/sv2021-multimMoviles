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
        var fechaEscogida = Calendar.getInstance()

        val btMostrar : Button = findViewById(R.id.btMostrar)
        val btGuardar : Button = findViewById(R.id.btGuardar)
        val btModificar : Button = findViewById(R.id.btModificar)
        val btBorrar : Button = findViewById(R.id.btBorrar)
        val btFecha : Button = findViewById(R.id.btFecha)
        val tvResultados : TextView = findViewById(R.id.tvResultados)
        val etId : EditText = findViewById(R.id.etId)
        val etNombre : EditText = findViewById(R.id.etNombre)
        val etRaza : EditText = findViewById(R.id.etRaza)
        val tvFecha : TextView = findViewById(R.id.tvFecha)

        val db : FirebaseFirestore = FirebaseFirestore.getInstance()
        var respuesta = ""

        val listenerFecha = DatePickerDialog.OnDateSetListener {
                datePicker, anyo, mes, dia ->
            fechaEscogida.clear()  // Para borrar H, M, S
            fechaEscogida.set(Calendar.YEAR, anyo)
            fechaEscogida.set(Calendar.MONTH, mes)
            fechaEscogida.set(Calendar.DAY_OF_MONTH, dia)
            tvFecha.setText(Date(fechaEscogida.timeInMillis).toString())
        }

        btFecha.setOnClickListener {
            var cal = Calendar.getInstance()
            DatePickerDialog(this, listenerFecha,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        btMostrar.setOnClickListener {
            db.collection("mascotas").get().
            addOnSuccessListener { resultado ->
                respuesta = ""
                for (documento in resultado) {
                    respuesta +=
                        documento.id.toString() + " - " +
                                documento["nombre"].toString() + " (" +
                                documento["raza"].toString() + ")"
                    var fecha = "(Fecha nac. no indicada)"
                    try {
                        if (documento["fecha"] != null)
                            fecha = (documento["fecha"] as Timestamp).toDate().toString()
                    } catch (e: Exception) { }
                    respuesta += " Nac: $fecha\n"
                }
            }.addOnFailureListener {
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
                    "raza" to etRaza.text.toString(),
                    "fecha" to Timestamp(
                        fechaEscogida.timeInMillis/1000,
                        0)
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