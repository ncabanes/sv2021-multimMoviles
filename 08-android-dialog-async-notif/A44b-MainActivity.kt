package com.nachocabanes.notifs01

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.nachocabanes.notifs01.databinding.ActivityMainBinding
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //setContentView(R.layout.activity_main)

        val timer = object: CountDownTimer(5000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() { notificar() }
        }

        binding.btNotificar.setOnClickListener {
            timer.start()
        }
    }

    fun notificar() {

        val cal = Calendar.getInstance()

        val textoBreve = "Temporizador terminado"
        val textoDetallado = "Terminado: ${cal.get(Calendar.DAY_OF_MONTH)}/" +
            "${cal.get(Calendar.MONTH) + 1}/${cal.get(Calendar.YEAR)} " +
            "${cal.get(Calendar.HOUR_OF_DAY)}:${cal.get(Calendar.MINUTE)}"

        val CHANNEL_ID = "com.nachocabanes.notifs01"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID,
                "Canal",
                NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = "Descripción"
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE)
                        as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        var notifBuilder = NotificationCompat.Builder(
            this,
            CHANNEL_ID
        )
        notifBuilder.setSmallIcon(R.mipmap.ic_launcher_round)
        notifBuilder.setContentTitle(textoBreve)
        notifBuilder.setContentText(textoDetallado)
        notifBuilder.priority =
            NotificationCompat.PRIORITY_DEFAULT
        NotificationManagerCompat.from(this)
            .notify(1234, notifBuilder.build())

        Toast.makeText(this, textoDetallado, Toast.LENGTH_SHORT).show()
    }
}