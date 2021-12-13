package com.nachocabanes.fragments02

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nachocabanes.fragments02.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)

        binding.btFragment1.setOnClickListener {
            val transaccion = supportFragmentManager.beginTransaction()
            val fragmento = Fragment1()
            transaccion.replace(R.id.contenedor, fragmento)
            transaccion.addToBackStack(null)
            transaccion.commit()
        }

        binding.btFragment2.setOnClickListener {
            val transaccion = supportFragmentManager.beginTransaction()
            val fragmento = Fragment2()
            transaccion.replace(R.id.contenedor, fragmento)
            transaccion.addToBackStack(null)
            transaccion.commit()
        }

    }
}