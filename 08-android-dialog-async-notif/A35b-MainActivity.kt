package com.nachocabanes.dialogs01

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import java.util.*

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

        val dateSetListener = DatePickerDialog.OnDateSetListener {
                picker, anyo, mes, dia ->
            Toast.makeText(this,"Fecha escogida: $dia/${mes+1}/$anyo",
                Toast.LENGTH_SHORT).show()
        }

        val timeSetListener = TimePickerDialog.OnTimeSetListener {
                timePicker, hora, minutos ->
            Toast.makeText(this,"Hora escogida: $hora:$minutos",
                Toast.LENGTH_SHORT).show()
        }

        val cal = Calendar.getInstance()

        DatePickerDialog( this, dateSetListener,
            cal.get(Calendar.YEAR),  cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        ).show()

        TimePickerDialog(this,
            timeSetListener,
            cal.get(Calendar.HOUR_OF_DAY),
            cal.get(Calendar.MINUTE),
            true  // Formato de 24 horas
        ).show()
    }
}