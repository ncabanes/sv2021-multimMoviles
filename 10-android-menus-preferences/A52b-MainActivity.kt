package com.nachocabanes.sharedpreferences

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.nachocabanes.sharedpreferences.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pref = applicationContext.getSharedPreferences(
            getString(R.string.ficheroPreferences), 0)
        val nombre = pref.getString(
            getString(R.string.campoNombre),
            "(Nombre no indicado)")
        val edad = pref.getInt(
            getString(R.string.campoEdad),
            20)

        Snackbar.make(
            binding.mainLayout,
            "Encontrado $nombre, con edad $edad",
            Snackbar.LENGTH_LONG
        ).setAction(
            "Entendido"
        ) {
            // Bla bla bla
        }.show()

        binding.btGuardar.setOnClickListener {
            val pref = applicationContext.
            getSharedPreferences(
                getString(R.string.ficheroPreferences),
                MODE_PRIVATE)
            val editor = pref.edit()
            editor.putString(
                getString(R.string.campoNombre),
                binding.etNombre.text.toString())
            editor.putInt(
                getString(R.string.campoEdad),
                binding.etEdad.text.toString().toInt())
            editor.apply()

            val builder = AlertDialog.Builder(this)
            builder.apply {
                setTitle("Guardado")
                setMessage("Datos guardados correctamente")
                setPositiveButton(android.R.string.ok, null)
                show()
            }


        }
    }
}