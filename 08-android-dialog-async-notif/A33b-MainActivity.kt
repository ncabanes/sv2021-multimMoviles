package com.nachocabanes.dialogs01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Título")
        builder.setMessage("Mensaje")
        builder.setPositiveButton(android.R.string.ok) { dialog, which ->
            Toast.makeText(this,
                "Aceptado",
                Toast.LENGTH_SHORT).show()
        }
        builder.setNegativeButton(android.R.string.cancel, null)
        builder.show()
    }
}