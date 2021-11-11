package com.nachocabanes.matricula

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btMatricula : Button = findViewById(R.id.btMatricular)
        val rbPrimero : RadioButton = findViewById(R.id.rbPrimero)
        val rbSegundo : RadioButton = findViewById(R.id.rbSegundo)
        val rbManana : RadioButton = findViewById(R.id.rbManana)
        val rbTarde : RadioButton = findViewById(R.id.rbTarde)
        val cbAD : CheckBox = findViewById(R.id.cbAD)
        val cbDI : CheckBox = findViewById(R.id.cbDI)
        val cbPMM : CheckBox = findViewById(R.id.cbPMM)
        val cbSGE : CheckBox = findViewById(R.id.cbSGE)

        var respuesta = ""
        btMatricula.setOnClickListener {
            if (rbPrimero.isChecked) respuesta += "Curso: Primero |"
            else if (rbSegundo.isChecked) respuesta += "Curso: Segundo |"

            if (rbManana.isChecked) respuesta += "Turno: mañana | "
            else if (rbTarde.isChecked) respuesta += "Turno: tarde | "

            if (cbAD.isChecked) respuesta += "AD "
            if (cbDI.isChecked) respuesta += "DI "
            if (cbPMM.isChecked) respuesta += "PMM "
            if (cbSGE.isChecked) respuesta += "SGE "

            Toast.makeText(applicationContext,
                respuesta, Toast.LENGTH_LONG).show()
        }
    }
}