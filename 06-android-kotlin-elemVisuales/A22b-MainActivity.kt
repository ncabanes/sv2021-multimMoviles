package com.jonathanaracil.recyclerview2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val adaptador : AdapterAnimal = AdapterAnimal()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvAnimales = rvAnimales
        rvAnimales.setHasFixedSize(true)
        rvAnimales.layoutManager = LinearLayoutManager(this)
        adaptador.AdapterAnimal(crearListaAnimales(), this)
        rvAnimales.adapter = adaptador

    }
    fun crearListaAnimales() : MutableList<Animal>{
        var animal: MutableList<Animal> = arrayListOf()
        animal.add(Animal("Perro", "Perrus", R.drawable.perro))
        animal.add(Animal("Gato", "Gatus", R.drawable.gato))
        animal.add(Animal("Mapache", "Mapachus", R.drawable.mapache))
        animal.add(Animal("Cisne", "Cisnus", R.drawable.cisne))
        return animal
    }
}