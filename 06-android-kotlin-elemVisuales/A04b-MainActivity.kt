package com.patricialopez.clickcounter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var textView = findViewById<TextView>(R.id.tvContador)
        var btnContador = findViewById<Button>(R.id.btnContador)
        var contador = 0;
        textView.text = contador.toString()
        btnContador.setOnClickListener {
            contador++
            textView.text = contador.toString()
        }
    }
}