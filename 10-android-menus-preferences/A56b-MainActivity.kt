package com.nachocabanes.menuview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    var actionMode: ActionMode? = null
    val actionModeCallback = object : ActionMode.Callback {
        override fun onCreateActionMode(mode: ActionMode?,
                                        menu: Menu?): Boolean {
            menuInflater.inflate(R.menu.menu_local, menu)
            return true
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
            return false
        }

        override fun onActionItemClicked(mode: ActionMode?,
                                         item: MenuItem?): Boolean {
            when (item!!.itemId) {
                R.id.opcEditar -> {
                    Toast.makeText(
                        applicationContext,
                        "Editar", Toast.LENGTH_SHORT
                    ).show()
                    return true
                }
                R.id.opcBorrar -> {
                    Toast.makeText(
                        applicationContext,
                        "Borrar", Toast.LENGTH_SHORT
                    ).show()
                    return true
                }
                else -> return false
            }
        }

        override fun onDestroyActionMode(mode: ActionMode?) {
            actionMode = null
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val imagen : ImageView = findViewById(R.id.imageView)
        imagen.setOnLongClickListener {
            if (actionMode == null) {
                actionMode = it.startActionMode(actionModeCallback)
                it.isSelected = true
                true
            }
            else false
        }
    }

}