package com.example.chardhamyatra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chardhamyatra.databinding.ActivityCitiesBinding
import com.example.chardhamyatra.databinding.ActivityLoginBinding

class CitiesActivity : AppCompatActivity() {

    //View binding
    private lateinit var binding: ActivityCitiesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.city1.setOnClickListener {
            startActivity(Intent(this, KedarnathActivity::class.java))
        }
        binding.city2.setOnClickListener {
            startActivity(Intent(this, BadrinathActivity::class.java))
        }
        binding.city3.setOnClickListener {
            startActivity(Intent(this, YamunotriActivity::class.java))
        }
        binding.city4.setOnClickListener {
            startActivity(Intent(this, GangotriActivity::class.java))
        }
    }
}