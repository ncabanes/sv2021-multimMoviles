package com.nachocabanes.ejemplosqlite

import android.database.sqlite.SQLiteDatabase
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

        binding.btMostrar.setOnClickListener {
            binding.tvConsulta.text = ""
            val db: SQLiteDatabase = mascotasDBHelper.readableDatabase
            val cursor = db.rawQuery(
                "SELECT * FROM mascotas ORDER BY nombre",
                null
            )

            if (cursor.moveToFirst()) {
                do {
                    binding.tvConsulta.append(
                        cursor.getInt(0).toString() + ": "
                    )
                    binding.tvConsulta.append(
                        cursor.getString(1).toString() + ", "
                    )
                    binding.tvConsulta.append(
                        cursor.getString(2).toString() + "\n"
                    )
                } while (cursor.moveToNext())
            }

        }

        binding.btBorrar.setOnClickListener {

            var cantidad = 0

            if (binding.etId.text.isNotBlank()) {
                cantidad = mascotasDBHelper.borrar(
                    binding.etId.text.toString().toInt()
                )
                binding.etId.text.clear()
            }

            Toast.makeText(
                this,
                "Datos borrados: $cantidad",
                Toast.LENGTH_LONG
            ).show()

        }


        binding.btModificar.setOnClickListener {
            if (binding.etNombre.text.isNotBlank() &&
                binding.etRaza.text.isNotBlank() &&
                binding.etId.text.isNotBlank()
            ) {

                mascotasDBHelper.modificar(
                    binding.etId.text.toString().toInt(),
                    binding.etNombre.text.toString(),
                    binding.etRaza.text.toString()
                )

                binding.etNombre.text.clear()
                binding.etRaza.text.clear()
                binding.etId.text.clear()

                Toast.makeText(
                    this, "Modificado",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    this,
                    "Los campos no deben estar vacíos",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}
