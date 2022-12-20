package com.example.goodday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import com.example.goodday.user.Logbook
import com.example.goodday.user.User
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import java.util.*

class LogbookEdit : AppCompatActivity() {

    lateinit var reference: DatabaseReference
    lateinit var user: FirebaseUser

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logbook_edit)

        val btn_back = findViewById<AppCompatImageButton>(R.id.btn_back)
        val btn_record = findViewById<AppCompatButton>(R.id.btn_record)
        val tv_airT = findViewById<TextView>(R.id.tv_airT)
        val tv_weather= findViewById<TextView>(R.id.tv_weather)
        val tv_wTemp= findViewById<TextView>(R.id.tv_wTemp)
        val tv_feeling= findViewById<TextView>(R.id.tv_feeling)
        val tv_bre= findViewById<TextView>(R.id.tv_bre)
        val tv_lun= findViewById<TextView>(R.id.tv_lun)
        val tv_din= findViewById<TextView>(R.id.tv_din)
        val tv_bT= findViewById<TextView>(R.id.tv_bT)
        val tv_bG= findViewById<TextView>(R.id.tv_bG)
        val tv_bP= findViewById<TextView>(R.id.tv_bP)
        val tv_pul= findViewById<TextView>(R.id.tv_pul)
        val tv_sle= findViewById<TextView>(R.id.tv_sle)
        val tv_exe= findViewById<TextView>(R.id.tv_exe)
        val tv_fun= findViewById<TextView>(R.id.tv_fun)
        val tv_wor= findViewById<TextView>(R.id.tv_wor)

        user = FirebaseAuth.getInstance().currentUser!!
        reference = FirebaseDatabase.getInstance().getReference("Logbook")
        val uid = user.uid

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val date = "$year-${month + 1}-$day"

        reference.child(uid).child(date).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val logbook = snapshot.getValue(Logbook::class.java) as Logbook

                if (logbook != null) {
                    val weather = logbook.weather
                    val airT = logbook.airT
                    val wirT = logbook.wirT
                    val feeling = logbook.feeling
                    val breakfast = logbook.breakfast
                    val lunch = logbook.lunch
                    val dinner = logbook.dinner
                    val tempB = logbook.tempB
                    val glucB = logbook.glucB
                    val presB = logbook.presB
                    val pulse = logbook.pulse
                    val sleep = logbook.sleep
                    val execise = logbook.execise
                    val funny = logbook.funny
                    val walk = logbook.walk
                    tv_airT.text = airT
                    tv_weather.text = weather
                    tv_wTemp.text = wirT
                    tv_feeling.text = feeling
                    tv_bre.text = breakfast
                    tv_lun.text = lunch
                    tv_din.text = dinner
                    tv_bT.text = tempB
                    tv_bG.text = glucB
                    tv_bP.text = presB
                    tv_pul.text = pulse
                    tv_sle.text = sleep
                    tv_exe.text = execise
                    tv_fun.text = funny
                    tv_wor.text = walk

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


        btn_back.setOnClickListener {
            finish()
        }

        btn_record.setOnClickListener {
            val intent = Intent(this, LogbookUpdate::class.java)
            startActivity(intent)

        }
    }

}