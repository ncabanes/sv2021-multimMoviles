package com.nachocabanes.ejemplosqlite

import android.content.Context
import android.database.Cursor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.nachocabanes.ejemplosqlite.databinding.ElementoRecyclerBinding

class RecyclerViewAdapterMascotas  :
    RecyclerView.Adapter<RecyclerViewAdapterMascotas.ViewHolder>(){

    private lateinit var context: Context
    private lateinit var cursor: Cursor

    fun  RecyclerViewAdapterMascotas(context: Context, cursor: Cursor) {
        this.context = context
        this.cursor = cursor
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder( inflater.inflate(
            R.layout.elemento_recycler,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        cursor.moveToPosition(position)
        holder.tvNombre.text = cursor.getString(1)
        holder.tvRaza.text = cursor.getString(2)
    }

    override fun getItemCount(): Int {
        return cursor.count
    }

    inner class ViewHolder: RecyclerView.ViewHolder {

        val tvNombre: TextView
        val tvRaza: TextView

        constructor(view: View) : super(view) {
            var bindingItemsRV = ElementoRecyclerBinding.bind(view)

            this.tvNombre = bindingItemsRV.tvNombreRV
            this.tvRaza = bindingItemsRV.tvRazaRV

            view.setOnClickListener {
                Toast.makeText(context,
                    "Escogido: ${this.tvNombre}",
                    Toast.LENGTH_SHORT).show()
            }

        }

    }


}