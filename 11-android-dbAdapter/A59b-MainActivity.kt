package com.nachocabanes.ejemplosqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.nachocabanes.ejemplosqlite.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var mascotasDBHelper: miSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mascotasDBHelper = miSQLiteHelper(this)

        binding.btGuardar.setOnClickListener {
            if (binding.etNombre.text.isNotBlank() &&
                binding.etRaza.text.isNotBlank()
            ) {
                mascotasDBHelper.anyadir(
                    binding.etNombre.text.toString(),
                    binding.etRaza.text.toString()
                )
                binding.etNombre.text.clear()
                binding.etRaza.text.clear()
                Toast.makeText(
                    this, "Guardado",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this, "No se ha podido guardar",
                    Toast.LENGTH_LONG
                ).show()
            }

        }
    }
}