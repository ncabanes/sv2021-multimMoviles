package com.nachocabanes.listview1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    /*
    val datos = arrayOf("Uno", "Dos", "Tres", "Cuatro",
        "Cinco", "Seis", "Siete", "Ocho",
        "Nueve", "Diez", "Once", "Doce",
        "Trece", "Catorce")
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datos = resources.getStringArray(R.array.datosLista)

        val adaptador = ArrayAdapter(this,
            android.R.layout.simple_list_item_1,
            datos)

        val lvEjemplo : ListView = findViewById(R.id.lvEjemplo)
        lvEjemplo.adapter = adaptador
    }
}