package com.jonathanaracil.listamenucontextual

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_datos.*

class ActivityDatos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_datos)

        btnGuardar.setOnClickListener {
            if (etDato.text.toString().isEmpty()) {
                val builder = AlertDialog.Builder(this)
                builder.setTitle("Cuidado")
                builder.setMessage("No has insertado ningún dato, ¿desea continuar?")
                builder.setPositiveButton(android.R.string.ok) { dialog, which ->
                    val resultadoIntent: Intent = Intent()
                    resultadoIntent.putExtra("dato", etDato.text.toString())
                    setResult(Activity.RESULT_OK, resultadoIntent)
                    finish()
                }
                builder.setNegativeButton(android.R.string.cancel, null)
                builder.show()
            }else{
                val resultadoIntent: Intent = Intent()
                resultadoIntent.putExtra("dato", etDato.text.toString())
                setResult(Activity.RESULT_OK, resultadoIntent)
                finish()
            }
        }
    }
}