package com.nachocabanes.ejemplosqlite

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class miSQLiteHelper(context: Context) :
    SQLiteOpenHelper(context, "mascotas.db",
        null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        try {
            val creacion = "CREATE TABLE mascotas " +
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nombre TEXT, " +
                    "raza TEXT)"
            db!!.execSQL(creacion)
        } catch (e: SQLiteException) {
            //Toast.makeText(this, "No se ha podido crear", Toast.LENGTH_LONG).show()
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        try {
            val drop = "DROP TABLE IF EXISTS mascotas"
            db!!.execSQL(drop)
            onCreate(db)
        } catch (e: SQLiteException) {
            // ...
        }

    }

    fun anyadir(nombre: String, raza: String) {
        val datos = ContentValues()
        datos.put("nombre", nombre)
        datos.put("raza", raza)

        val bd = this.writableDatabase
        bd.insert("mascotas",
            null,
            datos)
        bd.close()
    }

    fun borrar(id: Int): Int {
        val args = arrayOf(id.toString())
        val db = this.writableDatabase
        val borrados = db.delete("mascotas", "_id = ?", args)
        db.close()
        return borrados
    }

}