package com.nachocabanes.dialogs01

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.nachocabanes.dialogs01.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // setContentView(R.layout.activity_main)

        binding.btAlertSencillo.setOnClickListener {
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

        binding.btFecha.setOnClickListener {

            val cal = Calendar.getInstance()

            val dateSetListener = DatePickerDialog.OnDateSetListener {
                    picker, anyo, mes, dia ->
                Toast.makeText(this,"Fecha escogida: $dia/${mes+1}/$anyo",
                    Toast.LENGTH_SHORT).show()
            }

            DatePickerDialog( this, dateSetListener,
                cal.get(Calendar.YEAR),  cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.btHora.setOnClickListener {

            val cal = Calendar.getInstance()

            val timeSetListener = TimePickerDialog.OnTimeSetListener {
                    timePicker, hora, minutos ->
                Toast.makeText(this,"Hora escogida: $hora:$minutos",
                    Toast.LENGTH_SHORT).show()
            }

            TimePickerDialog(this,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true  // Formato de 24 horas
            ).show()
        }

        binding.btLista.setOnClickListener {
            val datos = arrayOf("Uno", "Dos", "Tres")
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Lista")
            builder.setItems(datos) {
                _, posicion ->
                Toast.makeText(this,
                    datos[posicion],
                    Toast.LENGTH_SHORT).show()
            }
            builder.show()
        }

        binding.btRadioButton.setOnClickListener {
            val datos = arrayOf("Uno", "Dos", "Tres")
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Lista")
            builder.setSingleChoiceItems(datos, -1) {
                    _, posicion ->
                Toast.makeText(this,
                    datos[posicion],
                    Toast.LENGTH_SHORT).show()
            }
            builder.setPositiveButton(android.R.string.ok) { dialogo, _ ->
                val posicionEscogida = (dialogo as AlertDialog).listView.checkedItemPosition
                if (posicionEscogida == -1)
                    Toast.makeText(this,
                        "Nada escogido",
                        Toast.LENGTH_LONG).show()
                else
                    Toast.makeText(this,
                        "Has escogido ${datos[posicionEscogida]}",
                        Toast.LENGTH_LONG).show()
            }
            builder.setNegativeButton(android.R.string.cancel, null)
            builder.show()
        }







    }
}