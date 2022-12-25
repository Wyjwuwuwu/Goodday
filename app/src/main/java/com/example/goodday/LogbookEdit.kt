package com.example.goodday

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
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
        val tv_bGf= findViewById<TextView>(R.id.tv_bGf)
        val tv_bGt= findViewById<TextView>(R.id.tv_bGt)
        val tv_gly= findViewById<TextView>(R.id.tv_gly)
        val tv_uri= findViewById<TextView>(R.id.tv_uri)
        val tv_bP= findViewById<TextView>(R.id.tv_bP)
        val tv_pul= findViewById<TextView>(R.id.tv_pul)
        val tv_sle= findViewById<TextView>(R.id.tv_sle)
        val tv_exe= findViewById<TextView>(R.id.tv_exe)
        val tv_fun= findViewById<TextView>(R.id.tv_fun)
        val tv_wor= findViewById<TextView>(R.id.tv_wor)
        val logbook_view = findViewById<LinearLayout>(R.id.logbook_view)
        val not_show = findViewById<LinearLayout>(R.id.not_show)
        val tv_date = findViewById<TextView>(R.id.tv_date)

        logbook_view.visibility = View.VISIBLE
        not_show.visibility = View.GONE

        user = FirebaseAuth.getInstance().currentUser!!
        reference = FirebaseDatabase.getInstance().getReference("Logbook")
        val uid = user.uid

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val date = "$year-${month + 1}-$day"

        tv_date.text = date

        reference.child(uid).child(date).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
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
                        val glucBf = logbook.glucBf
                        val glucBt = logbook.glucBt
                        val glys = logbook.gly
                        val uris = logbook.ur
                        val presB = logbook.presB
                        val pulse = logbook.pulse
                        val sleep = logbook.sleep
                        val execise = logbook.execise
                        val funny = logbook.funny
                        val walk = logbook.walk
                        tv_airT.text = "$airT°C"
                        tv_weather.text = weather
                        tv_wTemp.text = "$wirT°C"
                        tv_feeling.text = feeling
                        tv_bre.text = breakfast
                        tv_lun.text = lunch
                        tv_din.text = dinner
                        tv_bT.text = tempB
                        tv_bGf.text = glucBf
                        tv_bGt.text = glucBt
                        tv_gly.text = glys
                        tv_uri.text = uris
                        tv_bP.text = presB
                        tv_pul.text = pulse
                        tv_sle.text = sleep
                        tv_exe.text = execise
                        tv_fun.text = funny
                        tv_wor.text = walk
                    }else{
                        Toast.makeText(this@LogbookEdit, "no info", Toast.LENGTH_SHORT).show()
                    }
                }else{
                    logbook_view.visibility = View.GONE
                    not_show.visibility = View.VISIBLE
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


        btn_back.setOnClickListener {
            val intent = Intent(this, LogbookView::class.java)
            startActivity(intent)
            finish()
        }

        btn_record.setOnClickListener {
            val intent = Intent(this, LogbookUpdate::class.java)
            startActivity(intent)
        }
    }

}