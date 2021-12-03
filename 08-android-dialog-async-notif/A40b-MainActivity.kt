package com.nachocabanes.dialogs01

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.nachocabanes.dialogs01.databinding.ActivityMainBinding
import com.nachocabanes.dialogs01.databinding.ElementoDialogoBinding
import kotlinx.android.synthetic.main.elemento_dialogo.*
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var bindingDialogo: ElementoDialogoBinding

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
            builder.setTitle("Lista radiobutton")
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

        binding.btCheckBox.setOnClickListener {
            val escogidos = ArrayList<Int>()
            val datos = arrayOf("Uno", "Dos", "Tres")
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Lista checkbox")
            builder.setMultiChoiceItems(datos, null) { _, posicion, isChecked ->
                if (isChecked) {
                    escogidos.add(posicion)
                    Toast.makeText(this, "Activado $posicion",
                        Toast.LENGTH_LONG).show()
                }
                else  {
                    escogidos.remove(posicion)
                    Toast.makeText(this, "Desactivado $posicion",
                        Toast.LENGTH_LONG).show()
                }
            }

            builder.setPositiveButton(android.R.string.ok) {  dialogo, _ ->
                var respuesta = ""
                if (escogidos.size > 0)
                    for(item in escogidos)
                        respuesta += datos[item] + " "
                else
                    respuesta = "No se ha escogido ningún elemento"
                Toast.makeText( this, respuesta,
                    Toast.LENGTH_LONG).show()
            }

            builder.setNegativeButton(android.R.string.cancel, null)
            builder.show()
        }

        binding.btView.setOnClickListener {

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Acceso de usuario")
            val inflater = layoutInflater
            builder.setView(inflater.inflate(R.layout.elemento_dialogo, null))
            builder.setPositiveButton(android.R.string.ok) { dialogo, _ ->
                //val nombre = bindingDialogo.etNombre.text.toString()
                //val password = bindingDialogo.etPassword.text.toString()

                val nombre = (dialogo as AlertDialog).etNombre.text.toString()
                val password = (dialogo as AlertDialog).etPassword.text.toString()
                Toast.makeText(this, "Hola $nombre $password", Toast.LENGTH_LONG).show()
            }
            builder.setNegativeButton(android.R.string.cancel, null)
            builder.show()

        }
    }
}