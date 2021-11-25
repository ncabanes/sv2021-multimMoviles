package com.nachocabanes.intentsimplicitos01

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btWeb.setOnClickListener {
            val miIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://www.mua.ua.es/")
            )
            startActivity(miIntent)
        }

        btMapa.setOnClickListener {
            val miIntent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse("geo:38.3808085,-0.5138705,17z")
            )
            startActivity(miIntent)
        }
    }
}