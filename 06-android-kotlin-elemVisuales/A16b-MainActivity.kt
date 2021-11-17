package com.nity.spinner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.view.get
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.dias_semana,
            android.R.layout.simple_spinner_item
        )

        adapter.setDropDownViewResource(
            android.R.layout.simple_spinner_dropdown_item
        )

        spSemana.adapter = adapter

        spSemana.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                tvDiaEscogido.text = "${spSemana.getItemAtPosition(p2)}"

                Snackbar.make(layoutBase,
                    "Escogido: ${spSemana.getItemAtPosition(p2)}",
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction("Entendido"){}
                    .show()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                tvDiaEscogido.text = ""
            }
        }
    }
}