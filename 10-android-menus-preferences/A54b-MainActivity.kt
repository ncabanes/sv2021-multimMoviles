package com.jonathanaracil.listamenucontextual

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.RecyclerView
import com.jonathanaracil.listamenucontextual.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var datos = mutableListOf<String>()
    lateinit var binding: ActivityMainBinding
    lateinit var adaptador : ArrayAdapter<String>;
    var opcion = ""
    var posicionModificar = 0;
    var lanzador =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                if (data != null) {
                    val respuesta = "${data.getStringExtra("dato").toString()}"
                    if (opcion == "Modificar"){
                        datos.set(posicionModificar, respuesta)
                    }else {
                        datos.add(respuesta)
                    }
                    guardarDatos()
                }
            }
        }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        cargarDatos()
        adaptador = ArrayAdapter(this, android.R.layout.simple_list_item_1, datos)


        val lista : ListView = (binding.lvDatos)
        lista.adapter = adaptador
        registerForContextMenu(lista)
        binding.fabDatos.setOnClickListener{
            abrirVentana()
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menuInflater.inflate(R.menu.menu_contextual, menu)
    }


    override fun onContextItemSelected(item: MenuItem): Boolean {
        //Forma larga
        /*val info = item.menuInfo as AdapterView.AdapterContextMenuInfo
        val posicion = info.position*/
        val posicion = (item.menuInfo as AdapterView.AdapterContextMenuInfo).position
        val nombre = datos[posicion]
        when (item.itemId) {
            R.id.opcModificar -> {
                abrirVentana()
                Toast.makeText(this, "Modificar $nombre", Toast.LENGTH_SHORT).show()
                opcion = "Modificar"
                posicionModificar = posicion
                adaptador.notifyDataSetChanged()
                return true
            }
            R.id.opcEliminar -> {
                Toast.makeText(this, "Eliminar $nombre", Toast.LENGTH_SHORT).show()
                datos.removeAt(posicion)
                adaptador.notifyDataSetChanged()
                guardarDatos()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }
    fun abrirVentana(){
        val miIntent = Intent(this, ActivityDatos::class.java)
        lanzador.launch(miIntent)
    }
    fun guardarDatos(){
        var contador = 1;
        val cantidad = datos.size
        val pref = applicationContext.getSharedPreferences("datos", Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putInt("valor", cantidad)
        for (dato in datos){
            editor.putString("valor"+contador, dato)
            contador++;
        }
        editor.apply()
    }
    fun cargarDatos(){
        val pref = applicationContext.getSharedPreferences("datos", 0)
        val cantidad = pref.getInt("valor", 20)
        for (i in 1..cantidad){
            datos.add(pref.getString("valor"+i, "No se han encontraod datos")!!)
        }
    }
}