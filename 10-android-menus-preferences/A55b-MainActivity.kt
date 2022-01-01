package com.nachocabanes.menuview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun mostrarMenu(v: View) {
        PopupMenu(this, v).apply {
            inflate(R.menu.menu_local)
            setOnMenuItemClickListener {
                when(it!!.itemId) {
                    R.id.opcEditar -> {
                        Toast.makeText(
                            this@MainActivity,
                            "Editar", Toast.LENGTH_SHORT
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
}