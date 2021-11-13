package com.nachocabanes.listview1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

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

        lvEjemplo.onItemClickListener =
            object : AdapterView.OnItemClickListener {
                override fun onItemClick(
                        parent: AdapterView<*>?,
                        view: View?,
                        pos: Int,
                        id: Long)
                {
                    Snackbar.make(
                        view!!,
                        "Escogido: ${datos[pos]}",
                        Snackbar.LENGTH_INDEFINITE
                    ).setAction(
                        "Entendido"
                    ) {
                        // Bla bla bla
                    }.show()
                        /*
                        Toast.makeText(
                            applicationContext,
                            "Escogido: ${datos[pos]}",
                            Toast.LENGTH_SHORT
                        ).show()
                         */
                }
            }

    }
}
