package com.nachocabanes.listaactionmode2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.widget.AbsListView
import android.widget.ListView
import android.widget.Toast
import com.nachocabanes.listaactionmode2.R

class MainActivity : AppCompatActivity() {

    companion object {
        val datos: MutableList<String> =
            mutableListOf("uno", "dos", "tres", "cuatro",
                "cinco", "seis", "siete", "ocho",
                "nueve", "diez", "once", "doce" )
        var isActionMode: Boolean = false
        public var actionMode: ActionMode? = null
        var seleccion: MutableList<String> = ArrayList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adaptador = AdapterListaDatos(this, datos)
        val lista: ListView = findViewById(R.id.lvDatos)
        lista.adapter = adaptador
        lista.choiceMode = ListView.CHOICE_MODE_MULTIPLE_MODAL
        lista.setMultiChoiceModeListener( object: AbsListView.MultiChoiceModeListener {

            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                val inflater = menuInflater
                inflater.inflate(R.menu.menu_elemento, menu)
                actionMode = mode
                isActionMode = true
                return true
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return false
            }

            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                return when (item!!.itemId) {
                    R.id.opcBorrar -> {
                        Toast.makeText(this@MainActivity, "Borrado", Toast.LENGTH_LONG).show()
                        adaptador.borrarDatos(seleccion)
                        mode!!.finish()
                        true
                    }
                    R.id.opcModificar -> {
                        Toast.makeText(this@MainActivity, "Modificar", Toast.LENGTH_LONG).show()
                        true
                    }
                    else -> false
                }
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
                isActionMode = false
                actionMode = null
                seleccion.clear()
            }

            override fun onItemCheckedStateChanged(
                mode: ActionMode?,
                position: Int,
                id: Long,
                checked: Boolean
            ) {
                // (Gestionado desde el adaptador)
            }

        }
        )





    }
}