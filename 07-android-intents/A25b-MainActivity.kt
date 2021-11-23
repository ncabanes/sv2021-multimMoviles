package com.nachocabanes.intents01

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btSaludar.setOnClickListener {
            val miIntent = Intent(this,
                ActivitySaludo::class.java)
            miIntent.putExtra("nombre", etNombre.text.toString())
            startActivity(miIntent)

        }
    }
}