package com.nachocabanes.recycler02

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class AdapterAnimal :
    RecyclerView.Adapter<AdapterAnimal.ViewHolder>() {

    var animales: MutableList<Animal> = ArrayList()
    lateinit var contexto: Context

    fun AdapterAnimal(lista: MutableList<Animal>, contexto: Context) {
        this.animales = lista
        this.contexto = contexto
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterAnimal.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(    //  Nos falta crear este constructor
            layoutInflater.inflate(
                R.layout.elemento_animal,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: AdapterAnimal.ViewHolder,
        position: Int
    ) {
        holder.bind(animales[position], contexto)
    }

    override fun getItemCount(): Int {
        return animales.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNombre : TextView =
            view.findViewById(R.id.tvNombreCastellano)
        val tvLatin : TextView =
            view. findViewById(R.id.tvNombreLatin)
        val tvId : TextView =
            view. findViewById(R.id.tvIdentificador)

        fun bind(animal: Animal, context: Context) {

            tvNombre.text = animal.nombre
            tvLatin.text = animal.nombreLatin
            tvId.text = animal.id.toString()
            itemView.setOnClickListener {
                Toast.makeText(context, animal.nombre, Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}