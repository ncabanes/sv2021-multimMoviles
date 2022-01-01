package com.nachocabanes.recycler01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var datos = mutableListOf("Lunes", "Martes",
        "Miércoles")

    fun mostrarMenu(v: View, position: Int) {
        PopupMenu(this, v ).apply {
            inflate(R.menu.menu_contextual)
            setOnMenuItemClickListener {
                when(it!!.itemId) {
                    R.id.opcModif -> {
                        Toast.makeText(
                            this@MainActivity,
                            "Modificar ${datos[position]}", Toast.LENGTH_SHORT
                        ).show()
                        true
                    }
                    R.id.opcBorrar -> {
                        Toast.makeText(
                            this@MainActivity,
                            "Borrar", Toast.LENGTH_SHORT
                        ).show()
                        true
                    }
                    else -> false

                }

            }
        }.show()

    }

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

                holder.tvDatos.setOnLongClickListener {
                    mostrarMenu(holder.itemView, position)
                    true
                }
            }

            override fun getItemCount(): Int {
                return datos.size
            }
        }
    }


    inner class ViewHolder(view: View) :
        RecyclerView.ViewHolder(view) {

            var tvDatos : TextView =
                view.findViewById(android.R.id.text1)
    }
}