package com.nachocabanes.intents01

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var lanzador = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {

            val data: Intent? = result.data

            var respuesta = "Aceptado"
            if (data != null)
                respuesta = "Hola " + etNombre.text.toString() +
                        " " + data.getStringExtra("apellidos")
            Toast.makeText(
                this, respuesta,
                Toast.LENGTH_SHORT
            ).show()
        }

        if (result.resultCode == Activity.RESULT_CANCELED)
            Toast.makeText(this, "Cancelado",
                Toast.LENGTH_SHORT).show()

        }

    fun abrirSegundaVentanaYPasarDatos() {
        val miIntent = Intent(this, ActivitySaludo::class.java)
        miIntent.putExtra("nombre", etNombre.text.toString())
        lanzador.launch(miIntent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btSaludar.setOnClickListener {
            val miIntent = Intent(this,
                ActivitySaludo::class.java)
            miIntent.putExtra("nombre", etNombre.text.toString())
            startActivityForResult(miIntent, 1001)
        }

        btAnadir.setOnClickListener {
            abrirSegundaVentanaYPasarDatos()
        }
    }

    override fun onActivityResult(requestCode: Int,
                                  resultCode: Int,
                                  data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1001) {

            if (resultCode == Activity.RESULT_OK) {
                var respuesta = "Aceptado"
                if (data != null)
                    respuesta = "Hola " + etNombre.text.toString() +
                         " " + data.getStringExtra("apellidos")
                Toast.makeText(
                    this, respuesta,
                    Toast.LENGTH_SHORT
                ).show()
            }

            if (resultCode == Activity.RESULT_CANCELED)
                Toast.makeText(this, "Cancelado",
                    Toast.LENGTH_SHORT).show()
        }
    }
}