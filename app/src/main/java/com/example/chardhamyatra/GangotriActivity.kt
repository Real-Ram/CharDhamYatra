package com.example.chardhamyatra

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.chardhamyatra.databinding.ActivityGangotriBinding
import com.example.chardhamyatra.databinding.ActivityKedarnathBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class GangotriActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGangotriBinding

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGangotriBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        loadTheData()

        binding.attract12.setOnClickListener {
            startActivity(Intent(this, GGAttractActivity::class.java))
        }

        binding.travel12.setOnClickListener {
            startActivity(Intent(this, GgTravelActivity::class.java))
        }
    }

    private fun loadTheData() {
        val ref = FirebaseDatabase.getInstance().getReference("Gangotri")
        ref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val info = "${snapshot.child("Info").value}"
                val routes1 = "${snapshot.child("Routes1").value}"
                val routes2 = "${snapshot.child("Routes2").value}"
                val besttime = "${snapshot.child("BestTime").value}"


                binding.info12.text = info
                binding.routes121.text = routes1
                binding.routes122.text = routes2
                binding.besttime12.text = besttime

            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
}