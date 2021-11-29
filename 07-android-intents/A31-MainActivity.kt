package com.nico.intentsimplicitos01

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedReader

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnWeb.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.marqalicante.com"))

            startActivity(intent)
        }

        //Mapa 38.3539367,-0.4783183,17z
        btnMapa.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:38.3539367,-0.4783183,17z"))
            startActivity(intent)
        }

        btnLlamar.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:666999666"))
                startActivity(intent)
            } else {
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.CALL_PHONE, Manifest.permission.CALL_PRIVILEGED), 1234)
            }
        }
    }
}