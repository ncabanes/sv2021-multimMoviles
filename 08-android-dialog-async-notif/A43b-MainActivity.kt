package com.nachocabanes.notifs01

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.nachocabanes.notifs01.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)

        binding.btNotificar.setOnClickListener {

            val CHANNEL_ID = "com.nachocabanes.notifs01"

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(CHANNEL_ID,
                    "Canal",
                    NotificationManager.IMPORTANCE_DEFAULT)
                channel.description = "Descripción"
                // Y registramos el canal en el sistema
                val notificationManager =
                    getSystemService(Context.NOTIFICATION_SERVICE)
                            as NotificationManager
                notificationManager.createNotificationChannel(channel)
            }

            var notifBuilder = NotificationCompat.Builder(this,
                CHANNEL_ID)
            notifBuilder.setSmallIcon(R.mipmap.ic_launcher_round)
            notifBuilder.setContentTitle("Notificación")
            notifBuilder.setContentText("Notificación de prueba!!!")
            notifBuilder.priority =
                NotificationCompat.PRIORITY_DEFAULT
            NotificationManagerCompat.from(this)
                .notify(1234, notifBuilder.build())
        }
    }
}