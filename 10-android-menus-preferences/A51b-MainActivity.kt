package com.nachocabanes.menus01

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu1, menu )
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when ( item.itemId ) {
            R.id.ajustes -> {
                Toast.makeText(this, "Escogido: ajustes",
                    Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.error -> {
                Toast.makeText(this, "Escogido: informe de error",
                    Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
}