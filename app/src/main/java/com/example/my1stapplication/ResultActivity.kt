package com.example.my1stapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.my1stapplication.databinding.ActivityResultBinding
import kotlin.math.pow

class ResultActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityResultBinding.inflate(layoutInflater)
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

        binding.backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val height = intent.getFloatExtra("height", 0f)
        val weight = intent.getFloatExtra("weight", 0f)

        val bmi = weight / (height/100.0).pow(2.0)

        when{
            bmi >= 23 -> binding.resultTextView.text = "Not Fine"
            bmi >= 18.5 -> binding.resultTextView.text = "Fine"
            else -> binding.resultTextView.text = "A Little Fine"
        }

        when{
            bmi >= 23 ->
                binding.imageView.setImageResource(R.drawable.baseline_sentiment_very_dissatisfied_24)
            bmi >= 18.5 ->
                binding.imageView.setImageResource(R.drawable.baseline_sentiment_satisfied_alt_24)
            else ->
                binding.imageView.setImageResource(R.drawable.baseline_sentiment_neutral_24)
        }
        Toast.makeText(this, "$bmi", Toast.LENGTH_SHORT).show()
    }
}