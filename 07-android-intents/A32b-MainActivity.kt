package com.nachocabanes.todo

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*

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

        cargarDatos()

        var lanzador = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {

                val data: Intent? = result.data

                if (data != null) {
                    datos.add( data?.getStringExtra("detalles").toString() )
                    guardarDatos()
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

    private fun cargarDatos() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            val rutaSD = baseContext.getExternalFilesDir(null)?.absolutePath
            val ficheroFisico = File(rutaSD, "datos.txt")
            if (ficheroFisico.exists()) {
                val fichero = BufferedReader(InputStreamReader(FileInputStream(ficheroFisico)))
                datos = fichero.readLines().toMutableList()
                //(rvDatos.adapter as RecyclerView.Adapter).notifyDataSetChanged()
            }
        }
        else {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1001)
        }
    }

    private fun guardarDatos() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            val rutaSD = baseContext.getExternalFilesDir(null)?.absolutePath
            val ficheroFisico = File(rutaSD, "datos.txt")
            val fichero = PrintWriter( ficheroFisico )
            for (linea in datos)
                fichero.println(linea)
            fichero.close()
        }
        else {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1002)
        }
    }

}