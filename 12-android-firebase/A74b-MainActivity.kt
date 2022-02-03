package com.nachocabanes.firestore2021

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.gms.tasks.Task
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.nachocabanes.firestore2021.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var fechaEscogida = Calendar.getInstance()

        val db : FirebaseFirestore = FirebaseFirestore.getInstance()
        var respuesta = ""

        val listenerFecha = DatePickerDialog.OnDateSetListener {
                datePicker, anyo, mes, dia ->
            fechaEscogida.clear()  // Para borrar H, M, S
            fechaEscogida.set(Calendar.YEAR, anyo)
            fechaEscogida.set(Calendar.MONTH, mes)
            fechaEscogida.set(Calendar.DAY_OF_MONTH, dia)
            binding.tvFecha.setText(Date(fechaEscogida.timeInMillis).toString())
        }

        binding.btFecha.setOnClickListener {
            var cal = Calendar.getInstance()
            DatePickerDialog(this, listenerFecha,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        binding.btMostrar.setOnClickListener {
            val datos = db.collection("mascotas").get()
            mostrarDatos(datos)
        }

        binding.btGuardar.setOnClickListener {
            if (binding.etId.text.toString().isNotBlank()
                    && binding.etNombre.text.toString().isNotBlank()
                    && binding.etRaza.text.toString().isNotBlank()) {
                val mascota = hashMapOf(
                    "nombre" to binding.etNombre.text.toString(),
                    "raza" to binding.etRaza.text.toString(),
                    "fecha" to Timestamp(
                        fechaEscogida.timeInMillis/1000,
                        0)
                )

                db.collection("mascotas").document(binding.etId.text.toString())
                    .set(mascota)
                    .addOnSuccessListener { _ ->
                        binding.tvResultados.text = "Añadido"
                    }
                    .addOnFailureListener { _ ->
                        binding.tvResultados.text = "No se ha podido añadir"
                    }
            }
        }

        binding.btModificar.setOnClickListener {
            var mascota = hashMapOf<String,String>()
            var cambiado = false
            if (binding.etNombre.text.toString().isNotBlank()) {
                mascota["nombre"] = binding.etNombre.text.toString()
                cambiado = true
            }
            if (binding.etRaza.text.toString().isNotBlank()) {
                mascota["raza"] = binding.etRaza.text.toString()
                cambiado = true
            }

            if (cambiado) {
                db.collection("mascotas").document(binding.etId.text.toString())
                    .update(mascota.toMap())
                    .addOnSuccessListener { _ ->
                        binding.tvResultados.text = "Modificado"
                    }
                    .addOnFailureListener { _ ->
                        binding.tvResultados.text = "No se ha podido modificar"
                    }
            }
        }

        binding.btBorrar.setOnClickListener {
            if (binding.etId.text.toString().isNotBlank()) {
                db.collection("mascotas").document(binding.etId.text.toString())
                    .delete()
                    .addOnSuccessListener { _ ->
                        binding.tvResultados.text = "Borrado"
                    }
                    .addOnFailureListener { _ ->
                        binding.tvResultados.text = "No se ha podido borrar"
                    }
            }
        }

        binding.btConsultarPerros.setOnClickListener {
            val datos = db.collection("mascotas")
                .whereEqualTo("raza", "Perro")
                .get()
            mostrarDatos(datos)
        }

        binding.btConsultarInicialA.setOnClickListener {
            val datos = db.collection("mascotas")
                .whereGreaterThanOrEqualTo("nombre", "A")
                .whereLessThan("nombre", "B")
                .get()
            mostrarDatos(datos)
        }

        binding.btFecha2021.setOnClickListener {
            val cal = Calendar.getInstance()
            cal.set(Calendar.YEAR, 2021)
            cal.set(Calendar.MONTH, 0)
            cal.set(Calendar.DAY_OF_MONTH, 0)
            val datos = db.collection("mascotas")
                .whereGreaterThanOrEqualTo("fecha",
                    Timestamp(cal.timeInMillis/1000,0))
                .get()
            mostrarDatos(datos)
        }
    }

    private fun mostrarDatos(datos: Task<QuerySnapshot>) {
        var respuesta = ""
        binding.tvResultados.text = respuesta
        datos.addOnSuccessListener { resultado ->
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
                binding.tvResultados.text = respuesta
            }
        }.addOnFailureListener {
            respuesta = "Datos no encontrados"
            binding.tvResultados.text = respuesta
        }
    }
}