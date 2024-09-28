package com.example.my1stapplication

import android.content.Intent
import android.os.Bundle
import androidx.preference.PreferenceManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.my1stapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    private fun saveData(height: Float, weight: Float){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val editor = pref.edit()

        editor.putFloat("KEY_HEIGHT", height).putFloat("KEY_WEIGHT", weight).apply()
    }

    private fun loadData(){
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val height = pref.getFloat("KEY_HEIGHT", 0f)
        val weight = pref.getFloat("KEY_WEIGHT", 0f)

        if(height != 0f && weight != 0f){
            binding.heightEditText.setText(height.toString())
            binding.weightEditText.setText(weight.toString())
        }
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

        loadData()

        binding.resultButton2.setOnClickListener {
            if(binding.weightEditText.text.isNotBlank() && binding.heightEditText.text.isNotBlank()){
                saveData(
                    binding.heightEditText.text.toString().toFloat(),
                    binding.weightEditText.text.toString().toFloat(),
                )

                val intent = Intent(this, ResultActivity::class.java).apply{
                    putExtra("height", binding.heightEditText.text.toString().toFloat())
                    putExtra("weight", binding.weightEditText.text.toString().toFloat())
                }
                startActivity(intent)
            } else{
                Toast.makeText(this, "Enter the Height/Weight", Toast.LENGTH_SHORT).show()
            }
        }

        binding.StopWatchButton.setOnClickListener {
            val intent = Intent(this, StopWatchActivity::class.java)
            startActivity(intent)
        }
    }
}