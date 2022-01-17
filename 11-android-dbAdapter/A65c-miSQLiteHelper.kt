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

    companion object {
        val NOMBRE_TABLA = "mascotas"
        val CAMPO_ID = "_id"
        val CAMPO_NOMBRE = "nombre"
        val CAMPO_RAZA = "raza"
    }


    override fun onCreate(db: SQLiteDatabase?) {
        try {
            val creacion = "CREATE TABLE $NOMBRE_TABLA " +
                    "($CAMPO_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$CAMPO_NOMBRE TEXT, " +
                    "$CAMPO_RAZA TEXT)"
            db!!.execSQL(creacion)
        } catch (e: SQLiteException) {
            //Toast.makeText(this, "No se ha podido crear", Toast.LENGTH_LONG).show()
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        try {
            val drop = "DROP TABLE IF EXISTS $NOMBRE_TABLA"
            db!!.execSQL(drop)
            onCreate(db)
        } catch (e: SQLiteException) {
            // ...
        }

    }

    fun anyadir(nombre: String, raza: String) {
        val datos = ContentValues()
        datos.put(CAMPO_NOMBRE, nombre)
        datos.put(CAMPO_RAZA, raza)

        val bd = this.writableDatabase
        bd.insert(
            NOMBRE_TABLA,
            null,
            datos)
        bd.close()
    }

    fun borrar(id: Int): Int {
        val args = arrayOf(id.toString())
        val db = this.writableDatabase
        val borrados = db.delete(NOMBRE_TABLA,
            "$CAMPO_ID = ?", args)
        db.close()
        return borrados
    }

    fun modificar(id: Int, nombre: String, raza: String) {
        val args = arrayOf(id.toString())

        val datos = ContentValues()
        datos.put(CAMPO_NOMBRE, nombre)
        datos.put(CAMPO_RAZA, raza)

        val db = this.writableDatabase
        db.update(NOMBRE_TABLA, datos,
            "$CAMPO_ID = ?", args)
        db.close()
    }

}