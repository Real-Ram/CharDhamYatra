package com.example.chardhamyatra

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