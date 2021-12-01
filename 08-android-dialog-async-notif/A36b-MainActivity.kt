package com.nity.logs

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_registro.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data

                if(data != null) {

                    val resultado = "Desc: ${data.getStringExtra("descripcion")} " +
                            "F: ${data.getStringExtra("fecha")} " +
                            "H: ${data.getStringExtra("hora")} " +
                            "Prio: ${data.getStringExtra("prioridad")}"

                    Snackbar.make(
                        mainLayout,
                        resultado,
                        Snackbar.LENGTH_INDEFINITE
                    ).setAction("Entendido"){}.show()
                }
            }
            if(result.resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Has cancelado", Toast.LENGTH_SHORT).show()
            }
        }

        btPedirDatos.setOnClickListener {
            val miIntent = Intent(this, RegistroActivity::class.java)
            resultLauncher.launch(miIntent)
        }
    }
}