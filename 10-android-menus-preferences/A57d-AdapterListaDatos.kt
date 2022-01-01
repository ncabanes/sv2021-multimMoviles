package com.nachocabanes.listaactionmode2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import com.nachocabanes.listaactionmode2.R

class AdapterListaDatos(context: Context,
        var datos: List<String>) : BaseAdapter() {

    private val inflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)
                as LayoutInflater

    override fun getCount(): Int {
        return datos.size
    }

    override fun getItem(position: Int): String {
        return datos[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val viewFila = inflater.inflate(
            R.layout.elemento_lista,
        parent, false)
        val viewNombre : TextView = viewFila.findViewById(R.id.tvDato)
        val viewCheckBox : CheckBox = viewFila.findViewById(R.id.cbSeleccionado)

        viewNombre.text = getItem(position)
        viewCheckBox.tag = position

        if (MainActivity.isActionMode) {
            viewCheckBox.visibility = View.VISIBLE
        } else {
            viewCheckBox.visibility = View.GONE
        }

        viewCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
            val posicion = buttonView.tag as Int
            if (MainActivity.seleccion.contains(datos[posicion])) {
                MainActivity.seleccion.remove(datos[posicion])
            } else {  MainActivity.seleccion.add(datos[posicion]) }
            MainActivity.actionMode!!.title =
                "${MainActivity.seleccion.size} items seleccionados"
        }
        return viewFila
    }

    fun borrarDatos(datosABorrar: List<String>) {
        for (dato in datosABorrar) {
            MainActivity.datos.remove(dato)
        }
        notifyDataSetChanged()
    }
}
