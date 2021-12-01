package com.nity.logs

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_registro.*
import java.util.*

class RegistroActivity : AppCompatActivity() {

    fun introducirCero(num: Int): String{
        return if(num < 10) "0$num" else "$num"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        sbPrioridad.max = 5

        val cal = Calendar.getInstance()
        tvFecha.text = "${introducirCero(cal.get(Calendar.DAY_OF_MONTH))}/${introducirCero(cal.get(Calendar.MONTH) + 1)}/" +
                "${introducirCero(cal.get(Calendar.YEAR))}"
        tvHora.text = "${introducirCero(cal.get(Calendar.HOUR_OF_DAY) + 1)}/${introducirCero(cal.get(Calendar.MINUTE))}"
        val datePickerListener = DatePickerDialog.OnDateSetListener { dp, year, month, day ->
            Toast.makeText(this, "$day/$month/$year", Toast.LENGTH_SHORT).show()
            tvFecha.text = introducirCero(day) + "/" + introducirCero(month + 1) + "/" + introducirCero(year)
        }

        val timePickerListener = TimePickerDialog.OnTimeSetListener { dp, hour, minute ->
            Toast.makeText(this, "$hour/$minute", Toast.LENGTH_SHORT).show()
            tvHora.text = introducirCero(hour) + ":" + introducirCero(minute)
        }

        btFecha.setOnClickListener {
            DatePickerDialog(this,
                datePickerListener,
                cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH),
                cal.get(Calendar.DAY_OF_MONTH)).show()
        }

        btHora.setOnClickListener {
            TimePickerDialog(this,
                timePickerListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true).show()
        }

        sbPrioridad.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {
            }
            override fun onStopTrackingTouch(p0: SeekBar?) {
                Snackbar.make(
                    registroLayout,
                    "Prioridad: ${p0?.progress}",
                    Snackbar.LENGTH_INDEFINITE
                ).setAction("Entendido"){}.show()
            }
        })

        btGuardar.setOnClickListener {
            val resultadoIntent: Intent = Intent()
            resultadoIntent.putExtra("descripcion", etDescripcion.text.toString())
            resultadoIntent.putExtra("fecha", tvFecha.text.toString())
            resultadoIntent.putExtra("hora", tvHora.text.toString())
            resultadoIntent.putExtra("prioridad", sbPrioridad.progress.toString())

            setResult(RESULT_OK, resultadoIntent)
            finish()
        }
    }
}