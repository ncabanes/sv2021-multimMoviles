package com.nachocabanes.todo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_anadir.*

class ActivityAnadir : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anadir)

        btGuardar.setOnClickListener {

            if (etDetalles.text.toString().isNullOrEmpty()) {

                val builder = AlertDialog.Builder(this)
                builder.setTitle("Cuidado!")
                builder.setMessage("El texto está vacío. Confirme que desea guardarlo así.")
                builder.setPositiveButton(android.R.string.ok) { dialog, which ->
                    val resultadoIntent: Intent = Intent()
                    resultadoIntent.putExtra("detalles", etDetalles.text.toString())
                    setResult(Activity.RESULT_OK, resultadoIntent)
                    finish()
                }
                builder.setNegativeButton(android.R.string.cancel, null)
                builder.show()
            }
            else {
                val resultadoIntent: Intent = Intent()
                resultadoIntent.putExtra("detalles", etDetalles.text.toString())
                setResult(Activity.RESULT_OK, resultadoIntent)
                finish()
            }




        }
    }
}