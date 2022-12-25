package com.example.goodday

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.goodday.poster.DialogFragment
import com.example.goodday.user.HealthTrack
import com.example.goodday.user.User
import com.example.goodday.user.profile
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*


class MainActivity : AppCompatActivity(), DialogFragment.OnInputListener{

    private val TAG = "MainActivity"
    lateinit var user: FirebaseUser
    lateinit var uid: String

    override fun sendInput(input: String, time: String) {
        Log.d(TAG, "sendInput: got the input: $input, $time")

        mInput = input
        mTime = time

        setInputToTextView()
    }

    var mInput: String = ""
    var mTime: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)
        val navController = findNavController(R.id.main_fragment)
        bottomNavigationView.setupWithNavController(navController)

        user = FirebaseAuth.getInstance().currentUser!!
        uid = user.uid

    }
    fun setInputToTextView() {

        val profile = profile(mTime,mInput)
        FirebaseDatabase.getInstance().reference.child("Poster")
            .child(FirebaseAuth.getInstance().currentUser!!.uid).child(mTime)
            .setValue(profile).addOnCompleteListener { task ->
                if (task.isSuccessful){
                    Toast.makeText(this, "Post successfully", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Post failed", Toast.LENGTH_SHORT).show()
                }
            }
    }
}

