package com.example.laboratorios

import android.app.*
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import com.example.laboratorios.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var progressBarStatus = 0
        var rate = 0

        //seekbar
        lateinit var slider: SeekBar
        lateinit var value: TextView

        //date  picker and time lab
        binding.timeBtn.setOnClickListener {
            val c = Calendar.getInstance()
            val hour = c.get(Calendar.HOUR_OF_DAY)
            val minute = c.get(Calendar.MINUTE)

            val myTimePicker =
                TimePickerDialog(this,
                    android.R.style.ThemeOverlay,
                    { TimePicker, hourOfDay, minute ->
                        binding.timeText.text = "$hourOfDay: $minute"
                    }, hour, minute, true).show()
        }

        binding.BtnDate.setOnClickListener {
            val c = Calendar.getInstance()
            val day = c.get(Calendar.DAY_OF_MONTH)
            val month = c.get(Calendar.MONTH)
            val year = c.get(Calendar.YEAR)

            val myDatePicker =
                DatePickerDialog(this,
                    android.R.style.ThemeOverlay,
                    { DatePicker, Year, Month, Day ->
                        binding.dateText.text = "$day/ ${month + 1}/ $year"
                    },
                    year,
                    month,
                    day).show()
        }


        binding.BtnIntent.setOnClickListener(){
            startActivity(Intent(this, com.example.laboratorios.AlertDialog::class.java))

        }
        binding.btnWebV.setOnClickListener(){
            startActivity(Intent(this, Webview::class.java))
        }
        binding.BtnRatingBar.setOnClickListener(){
            startActivity(Intent(this, Ratingbar::class.java))

        }

        slider = binding.mySeekBar
        value = binding.seekBarResult

        slider.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(
                seekBar: SeekBar?,
                progress: Int,
                fromUser: Boolean,
            ) {
                value.text = progress.toString()

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
                value.text = "Usted ha seleccionado ${slider.progress}"

            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
                value.text = "Usted ha seleccionado ${slider.progress}"

            }
        })

        binding.btnProgressBar.setOnClickListener() { view ->
            Thread(Runnable {
                while (progressBarStatus < 100) {
                    try {
                        rate += 10
                        Thread.sleep(500)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    progressBarStatus = rate
                    binding.progressBar.progress = progressBarStatus
                }
                progressBar.visibility = ProgressBar.INVISIBLE
            }).start()
        }
        binding.btnProgressBar3.setOnClickListener() { view ->
            Thread(Runnable {
                while (progressBarStatus < 100) {
                    try {
                        rate += 10
                        Thread.sleep(500)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                    progressBarStatus = rate
                    binding.progressBar2.progress = progressBarStatus
                }
                progressBar2.visibility = ProgressBar.INVISIBLE
            }).start()
            binding.btnStop.setOnClickListener() {
                progressBar2.visibility = View.GONE
            }
        }
    }

    fun msSnack(view: View) {
        Snackbar.make((binding.btnSnackbar),
            "Your email has been sent susccesfully",
            Snackbar.LENGTH_LONG).show()
    }

    fun msSnackOption(view: View) {
        Snackbar.make((binding.btnSnackbarOptions),
            "Your email has been sent susccesfully",
            Snackbar.LENGTH_LONG).setAction("OK") {}.setTextColor(Color.GRAY)
            .setActionTextColor(Color.CYAN).show()


    }

    fun Alerta(view: View) {
        val dialog = AlertDialog.Builder(this)
        dialog.setTitle("Guardar")
        dialog.setMessage("Esto es una clase practica de AlertDialog")
        dialog.setPositiveButton("De acuerdo") { dialogInterface: DialogInterface, _: Int ->
            Snackbar.make((binding.btnAlerta),
                "Guardado",
                Snackbar.LENGTH_LONG).show()
        }
        dialog.show()

        dialog.setNegativeButton("No estoy de acuerdo") { dialogInterface: DialogInterface, _: Int ->
            Snackbar.make((binding.btnAlerta),
                "Cancelado",
                Snackbar.LENGTH_LONG).show()
        }
        dialog.show()

        dialog.setNeutralButton("Luego lo hago", null)
        dialog.show()
    }

    fun courseUpdate(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            //Canal de notififaciones
            val channel_id = "Channe_01"
            val channel_name = "Notification"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(channel_id, channel_name, importance)
            mChannel.description = "Test Description"
            mChannel.enableLights(true)
            mChannel.lightColor = Color.CYAN
            mChannel.enableVibration(true)

            //Usa notification builder para agregar notificacion
            val notification: Notification = Notification.Builder(this, channel_id)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Prueba Notificacion")
                .setContentText("Mira esta notificacion que acabo de crear")

                .build()

            // Registrar o agregar el canal con el sistema
            val mNotificationManager =
                getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            if (mNotificationManager != null) {
                mNotificationManager.createNotificationChannel(mChannel)
                //Muestra la notificacion
                mNotificationManager.notify(1, notification)

            } else {
                //cuando el sdk esta abajo de la version 26
                val notification: Notification = NotificationCompat.Builder(this, channel_id)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentTitle("Prueba Notificacion")
                    .setContentText("Mira esta notificacion que acabo de crear")
                    .build()
                mNotificationManager.notify(1, notification)
            }
            //Intent desde la notificacion
            val intent = Intent(this, com.example.laboratorios.AlertDialog::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)

            //Usa builder para agregar notifiacion
            val notification2: Notification = Notification.Builder(this, channel_id)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Prueba Notificacion")
                .setContentText("Mira esta notificacion que acabo de crear")
                .setContentIntent(pendingIntent)
                .build()
        }
    }

}





