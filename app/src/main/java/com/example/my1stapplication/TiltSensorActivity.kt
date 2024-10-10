package com.example.my1stapplication

import android.content.Context
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class TiltSensorActivity : AppCompatActivity(), SensorEventListener {
//    private val binding by lazy{
//        ActivityTiltSensorBinding.inflate(layoutInflater)
//    }
    private val sensorManager by lazy {
        getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this,
            sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
            SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
//
    override fun onSensorChanged(event: SensorEvent?) {
        event?.let{
            Log.d("Activity", "x: ${event.values[0]}, y: ${event.values[1]}, z: ${event.values[2]}")
        }

    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {
//        TODO("Not yet implemented")
    }

    private lateinit var tiltView: TiltView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
//        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        tiltView = TiltView(this)
        setContentView(tiltView)
    }

}