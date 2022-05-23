package com.example.chardhamyatra

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.chardhamyatra.databinding.ActivityCitiesBinding
import com.example.chardhamyatra.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class CitiesActivity : AppCompatActivity() {

    //View binding
    private lateinit var binding: ActivityCitiesBinding

    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCitiesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()
        loadTheData()

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

        binding.logoutBtn.setOnClickListener {

            val builder = AlertDialog.Builder(this)
            builder.setTitle("LOGOUT")
                .setMessage("Are you sure you want to logout now?")
                .setPositiveButton("Confirm"){ a, d->
                    Toast.makeText(this, "Logging out....", Toast.LENGTH_SHORT).show()
                    FirebaseAuth.getInstance().signOut()
                    startActivity(Intent(this, MainActivity::class.java))
                    Toast.makeText(this, "Successfully Log out....", Toast.LENGTH_SHORT).show()

                }
                .setNegativeButton("Cancel"){a, d->
                    a.dismiss()
                }
                .show()
        }
    }

    private fun loadTheData() {
        val ref = FirebaseDatabase.getInstance().getReference("Chardham")
        ref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val info1 = "${snapshot.child("Info1").value}"
                val info2 = "${snapshot.child("Info2").value}"

                binding.info12.text = info1
                binding.info22.text = info2
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }

}