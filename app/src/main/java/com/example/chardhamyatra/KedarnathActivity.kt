package com.example.chardhamyatra

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.chardhamyatra.databinding.ActivityKedarnathBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener



class KedarnathActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKedarnathBinding

    private lateinit var firebaseAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKedarnathBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        loadTheData()

        binding.attract12.setOnClickListener {
            startActivity(Intent(this, KDAttractActivity::class.java))
        }

        binding.travel12.setOnClickListener {
            startActivity(Intent(this, KdTravelActivity::class.java))
        }
    }

    private fun loadTheData() {
        val ref = FirebaseDatabase.getInstance().getReference("Kedarnath")
        ref.addValueEventListener(object: ValueEventListener{
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