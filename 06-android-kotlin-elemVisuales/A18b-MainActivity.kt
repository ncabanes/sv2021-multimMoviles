package com.nachocabanes.logd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dias = arrayOf("lunes", "martes", "miércoles",
            "jueves", "viernes", "sábado", "domingo")

        val adaptador = ArrayAdapter(this,
            android.R.layout.simple_list_item_1,
            dias)

        lvDiasSemana.adapter = adaptador

        lvDiasSemana.onItemClickListener =
            object : AdapterView.OnItemClickListener {
                override fun onItemClick(
                    parent: AdapterView<*>?,
                    view: View?,
                    pos: Int,
                    id: Long)
                {
                    Log.d("Dias de la semana", "Escogido: ${dias[pos]}")
                }
            }

        }
}