package com.nachocabanes.todo

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var datos = mutableListOf("Lunes", "Martes",
        "Miércoles")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvDatos.layoutManager = LinearLayoutManager(this)
        rvDatos.adapter = object : RecyclerView.Adapter<ViewHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                return ViewHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(android.R.layout.simple_list_item_1, parent, false))
            }

            override fun onBindViewHolder(holder: ViewHolder, position: Int) {
                holder.tvDatos.text = datos[position]
                holder.tvDatos.setOnClickListener {
                    Toast.makeText(applicationContext,
                        datos[position],
                        Toast.LENGTH_SHORT).show()
                }
            }

            override fun getItemCount(): Int {
                return datos.size
            }

        }

        var lanzador = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {

                val data: Intent? = result.data

                if (data != null) {
                    datos.add( data?.getStringExtra("detalles").toString() )
                    Toast.makeText(
                        this, data?.getStringExtra("detalles"),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        fun abrirSegundaVentana() {
            val miIntent = Intent(this, ActivityAnadir::class.java)
            lanzador.launch(miIntent)
        }

        btAnadir.setOnClickListener {
            abrirSegundaVentana();
        }

        // Ejemplo de como añadir datos y actualizar el RV
        datos.add("Jueves")
        (rvDatos.adapter as RecyclerView.Adapter).notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

        var tvDatos : TextView =
            view.findViewById(android.R.id.text1)

    }
}