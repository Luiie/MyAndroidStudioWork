package com.example.my1stapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.my1stapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val clickButton = findViewById<Button>(R.id.resultButton1)
        var clicked = true
        val textview = findViewById<TextView>(R.id.textView1)

        clickButton.setOnClickListener{
            if (clicked){
                textview.text = "CLICKED!!!"
            } else{
                textview.text = "Hello World!"
            }
            clicked = !clicked
        }

        binding.resultButton2.setOnClickListener {
            val intent = Intent(this, ResultActivity::class.java).apply{
                putExtra("height", binding.heightEditText.text.toString().toFloat())
                putExtra("weight", binding.weightEditText.text.toString().toFloat())
            }
            startActivity(intent)
        }
    }

    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }
}