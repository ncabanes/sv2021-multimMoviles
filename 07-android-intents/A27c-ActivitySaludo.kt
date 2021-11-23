package com.nachocabanes.intents01

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_saludo.*

class ActivitySaludo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saludo)

        val nombre : String? = intent.getStringExtra("nombre")
        tvHola.text = "Hola " + nombre + ", dime tu apellido"

        btAceptar.setOnClickListener {

            // setResult(Activity.RESULT_OK)

            val resultadoIntent : Intent = Intent()
            resultadoIntent.putExtra("apellidos", etApellido.text.toString())
            setResult(Activity.RESULT_OK, resultadoIntent)

            finish()
        }

        btCancelar.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }


}