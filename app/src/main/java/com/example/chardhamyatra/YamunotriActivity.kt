package com.example.chardhamyatra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chardhamyatra.databinding.ActivityYamunotriBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class YamunotriActivity : AppCompatActivity() {
    private lateinit var binding: ActivityYamunotriBinding

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityYamunotriBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        loadTheData()

        binding.attract12.setOnClickListener {
            startActivity(Intent(this, YMAttractActivity::class.java))
        }

        binding.travel12.setOnClickListener {
            startActivity(Intent(this, YmTravelActivity::class.java))
        }

        binding.map12.setOnClickListener {
            startActivity(Intent(this,YamunotriMap::class.java))
        }

        binding.tour12.setOnClickListener {
            startActivity(Intent(this,WebYmActivity::class.java))
        }

        binding.weather12.setOnClickListener {
            startActivity(Intent(this,WeatherYmActivity::class.java))
        }
    }

    private fun loadTheData() {
        val ref = FirebaseDatabase.getInstance().getReference("Yamunotri")
        ref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val info = "${snapshot.child("Info").value}"
                val routes = "${snapshot.child("Routes").value}"
                val besttime = "${snapshot.child("BestTime").value}"


                binding.info12.text = info
                binding.routes12.text = routes
                binding.besttime12.text = besttime

            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}