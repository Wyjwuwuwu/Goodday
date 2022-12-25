package com.example.goodday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.goodday.user.HealthTrack
import com.example.goodday.user.Logbook
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class LogbookUpdate : AppCompatActivity() {

    lateinit var user: FirebaseUser
    lateinit var uid: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logbook_update)

        user = FirebaseAuth.getInstance().currentUser!!
        uid = user.uid

        val et_weather = findViewById<EditText>(R.id.et_weather)
        val et_airT = findViewById<EditText>(R.id.et_airT)
        val et_wTemp = findViewById<EditText>(R.id.et_wTemp)
        val et_feeling = findViewById<EditText>(R.id.et_feeling)
        val et_bre = findViewById<EditText>(R.id.et_bre)
        val et_lun = findViewById<EditText>(R.id.et_lun)
        val et_din = findViewById<EditText>(R.id.et_din)
        val et_bT = findViewById<EditText>(R.id.et_bT)
        val et_bG = findViewById<EditText>(R.id.et_bG)
        val et_bP = findViewById<EditText>(R.id.et_bP)
        val et_pul = findViewById<EditText>(R.id.et_pul)
        val et_sle = findViewById<EditText>(R.id.et_sle)
        val et_wor = findViewById<EditText>(R.id.et_wor)
        val et_fun = findViewById<EditText>(R.id.et_fun)
        val et_exe = findViewById<EditText>(R.id.et_exe)
        val btn_update = findViewById<Button>(R.id.btn_update)

        btn_update.setOnClickListener{
            val weather = et_weather.text.toString()
            val airT = et_airT.text.toString()
            val wTemp = et_wTemp.text.toString()
            val feeling = et_feeling.text.toString()
            val bre = et_bre.text.toString()
            val lun = et_lun.text.toString()
            val din = et_din.text.toString()
            val bt = et_bT.text.toString()
            val bg = et_bG.text.toString()
            val bp = et_bP.text.toString()
            val pul = et_pul.text.toString()
            val sle = et_sle.text.toString()
            val wor = et_wor.text.toString()
            val funny = et_fun.text.toString()
            val exe = et_exe.text.toString()

            val logbook = Logbook(weather,airT, wTemp, feeling, bre, lun,din,bt,bp,bg,pul,sle,exe,funny,wor)

            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val date = "$year-${month + 1}-$day"

            FirebaseDatabase.getInstance().reference.child("Logbook")
                .child(FirebaseAuth.getInstance().currentUser!!.uid)
                .child(date)
                .setValue(logbook).addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "update up successfully", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "update up failed", Toast.LENGTH_SHORT).show()
                    }
                }

            val intent = Intent(this,LogbookEdit::class.java)
            startActivity(intent)
        }




    }
}