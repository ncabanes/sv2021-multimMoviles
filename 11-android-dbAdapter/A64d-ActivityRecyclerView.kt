package com.nachocabanes.ejemplosqlite

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nachocabanes.ejemplosqlite.databinding.ActivityRecyclerViewBinding

class ActivityRecyclerView : AppCompatActivity() {

    private lateinit var binding: ActivityRecyclerViewBinding
    private val mascotasDBHelper = miSQLiteHelper(this)
    private lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = mascotasDBHelper.readableDatabase
        val cursor = db.rawQuery(
            "SELECT * FROM mascotas ORDER BY nombre",
            null)

        val adaptador = RecyclerViewAdapterMascotas()
        adaptador.RecyclerViewAdapterMascotas(this, cursor)
        binding.rvDatos.setHasFixedSize(true)
        binding.rvDatos.layoutManager = LinearLayoutManager(this)
        binding.rvDatos.adapter = adaptador
    }

    override fun onDestroy() {
        super.onDestroy()
        db.close()
    }
}