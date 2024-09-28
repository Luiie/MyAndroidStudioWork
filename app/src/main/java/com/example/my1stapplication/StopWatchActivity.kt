package com.example.my1stapplication

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.my1stapplication.databinding.ActivityStopWatchBinding
import java.util.Timer
import kotlin.concurrent.timer

class StopWatchActivity : AppCompatActivity() {
    private var time = 0
    private var timerTask: Timer? = null
    private var isRunning = false
    private var lap = 1

    private val binding by lazy{
        ActivityStopWatchBinding.inflate(layoutInflater)
    }

    private fun reset() {
        timerTask?.cancel()

        time = 0
        isRunning = false

        binding.startActionButton.setImageResource(R.drawable.baseline_play_arrow_24)
        binding.secTextView.text = "0"
        binding.milliTextView.text = "00"
        binding.lapLayout.removeAllViews()
    }

    private fun start() {
        binding.startActionButton.setImageResource(R.drawable.baseline_pause_24)
        timerTask = timer(period = 10) {
            time++
            val sec = time/100
            val milli = time%100
            runOnUiThread{
                binding.secTextView.text = "$sec"
                binding.milliTextView.text = "$milli"
            }
        }
    }

    private fun pause() {
        binding.startActionButton.setImageResource(R.drawable.baseline_play_arrow_24)
        timerTask?.cancel()
    }

    private fun recordLapTime() {
        val lapTime = this.time
        val textView = TextView(this)
        textView.text = "$lap LAP: ${lapTime / 100}.${lapTime % 100}"

        binding.lapLayout.addView(textView, 0)
        lap++
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.resetActionButton.setOnClickListener {
            reset()
        }

        binding.startActionButton.setOnClickListener {
            isRunning = !isRunning
            if (isRunning) {
                start()
            } else {
                pause()
            }
        }

        binding.LapTimeButton.setOnClickListener {
            recordLapTime()
        }

        binding.MainButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}