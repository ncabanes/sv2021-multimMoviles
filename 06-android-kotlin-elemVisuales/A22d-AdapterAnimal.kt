package com.jonathanaracil.recyclerview2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.elemento_animal.view.*

class AdapterAnimal : RecyclerView.Adapter<AdapterAnimal.ViewHolder>() {
    var listaAnimales: MutableList<Animal> = ArrayList()
    lateinit var contexto: Context

    fun AdapterAnimal(lista: MutableList<Animal>, contexto: Context){
        this.listaAnimales = lista
        this.contexto = contexto
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterAnimal.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(layoutInflater.inflate(R.layout.elemento_animal, parent, false))
    }

    override fun onBindViewHolder(holder: AdapterAnimal.ViewHolder, position: Int) {
        holder.bind(listaAnimales[position], contexto)
    }

    override fun getItemCount(): Int {
        return listaAnimales.size
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvNombre = view.tvNombre as TextView
        val tvLatin = view.tvNombreLatin as TextView
        val ivAnimal = view.ivAnimal as ImageView
        fun bind(animal: Animal, contexto: Context){
            tvNombre.text = animal.nombre
            tvLatin.text = animal.latin
            ivAnimal.setImageResource(animal.imageAnimal!!)
            itemView.setOnClickListener {
                Toast.makeText(contexto, animal.nombre, Toast.LENGTH_SHORT).show()
            }
        }
    }
}