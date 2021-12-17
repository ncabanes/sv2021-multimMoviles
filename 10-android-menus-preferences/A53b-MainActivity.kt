package com.nachocabanes.listamenu

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import com.nachocabanes.listamenu.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    val datos = arrayOf("uno", "dos", "tres", "cuatro",
        "cinco", "seis", "siete", "ocho",
        "nueve", "diez", "once", "doce",
        "trece", "catorce", "quince")

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adaptador = ArrayAdapter(this,
            android.R.layout.simple_list_item_1,
            datos)

        binding.lista.adapter = adaptador
        registerForContextMenu(binding.lista)
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        menuInflater.inflate(
            R.menu.menu_contextual,
            menu)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        val posicion =
            (item.menuInfo as AdapterView.AdapterContextMenuInfo)
                .position
        val texto = datos[posicion]
        if (item.itemId == R.id.opcEliminar) {
            Toast.makeText(this, "Eliminar $texto", Toast.LENGTH_SHORT).show()
            return true
        }
        else if (item.itemId == R.id.opcModificar) {
            Toast.makeText(this, "Modificar $texto", Toast.LENGTH_SHORT).show()
            return true
        }
        else return super.onContextItemSelected(item)
    }
}