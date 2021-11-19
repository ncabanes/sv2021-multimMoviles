package com.nachocabanes.recycler02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private val adaptador : AdapterAnimal = AdapterAnimal()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvAnimales = findViewById(R.id.rvAnimales) as RecyclerView
        rvAnimales.setHasFixedSize(true)
        rvAnimales.layoutManager = LinearLayoutManager(this)
        adaptador.AdapterAnimal(crearListaAnimales(), this)    // Falta esta función
        rvAnimales.adapter = adaptador



    }

    private fun crearListaAnimales(): MutableList<Animal> {
        val animales: MutableList<Animal> = arrayListOf()
        animales.add(Animal("Cisne", "Cygnus olor", 1 as Integer))
        animales.add(Animal("Erizo", "Erinaceinae", 2 as Integer))
        animales.add(Animal("Gato", "Felis catus", 3 as Integer))
        return animales
    }

}