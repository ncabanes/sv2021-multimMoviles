package com.nachocabanes.ejemplosqlite

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CursorAdapter
import android.widget.Toast
import com.nachocabanes.ejemplosqlite.databinding.ActivityListViewBinding
import com.nachocabanes.ejemplosqlite.databinding.ElementoListaBinding

class ActivityListView : AppCompatActivity() {

    lateinit var binding: ActivityListViewBinding
    lateinit var mascotasDBHelper: miSQLiteHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mascotasDBHelper = miSQLiteHelper(this)
        val db : SQLiteDatabase = mascotasDBHelper.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM mascotas",
            null)

        val adaptador = CursorAdapterListView(this, cursor)
        binding.lvDatos.adapter = adaptador
        db.close()
    }

    inner class CursorAdapterListView(context: Context, cursor: Cursor) :
        CursorAdapter(context, cursor, FLAG_REGISTER_CONTENT_OBSERVER) {

        override fun newView(context: Context?,
                             cursor: Cursor?, parent: ViewGroup?): View {
            val inflater = LayoutInflater.from(context)
            return inflater.inflate(R.layout.elemento_lista,
                parent, false)
        }

        override fun bindView(view: View?, context: Context?,
                              cursor: Cursor?) {
            val bindingItems = ElementoListaBinding.bind(view!!)
            bindingItems.tvNombre.text = cursor!!.getString(1)
            bindingItems.tvRaza.text = cursor!!.getString(2)

            view.setOnClickListener {
                Toast.makeText(this@ActivityListView,
                    "Escogido ${bindingItems.tvNombre.text}",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}