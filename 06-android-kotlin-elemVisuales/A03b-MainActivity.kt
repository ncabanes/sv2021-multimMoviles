package com.nachocabanes.textosybotones

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var textView : TextView = findViewById(R.id.tvSaludo)
        var btSaludar = findViewById<Button>(R.id.btSaludar)

        btSaludar.setOnClickListener {
            textView.text = "Hola"
        }
    }
}