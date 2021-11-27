package com.nachocabanes.todo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_anadir.*

class ActivityAnadir : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_anadir)

        btGuardar.setOnClickListener {
            val resultadoIntent : Intent = Intent()
            resultadoIntent.putExtra("detalles", etDetalles.text.toString())
            setResult(Activity.RESULT_OK, resultadoIntent)

            finish()
        }
    }
}