package com.nachocabanes.intents01

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_saludo.*

class ActivitySaludo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saludo)

        val nombre : String? = intent.getStringExtra("nombre")
        tvHola.text = "Hola " + nombre

        btAceptar.setOnClickListener {
            setResult(Activity.RESULT_OK)
            finish()
        }

        btCancelar.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }


}