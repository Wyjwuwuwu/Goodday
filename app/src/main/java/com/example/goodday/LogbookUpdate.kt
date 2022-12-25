package com.example.goodday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import com.example.goodday.user.HealthTrack
import com.example.goodday.user.Logbook
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import java.util.*

class LogbookUpdate : AppCompatActivity() {

    lateinit var user: FirebaseUser
    lateinit var reference: DatabaseReference
    lateinit var reference2: DatabaseReference
    lateinit var uid: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logbook_update)

        user = FirebaseAuth.getInstance().currentUser!!
        reference = FirebaseDatabase.getInstance().getReference("Logbook")
        reference2 = FirebaseDatabase.getInstance().getReference("Health_Track")
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
        val et_bGt = findViewById<EditText>(R.id.et_bGt)
        val et_gly = findViewById<EditText>(R.id.et_gly)
        val et_ur = findViewById<EditText>(R.id.et_ur)
        val et_bP = findViewById<EditText>(R.id.et_bP)
        val et_pul = findViewById<EditText>(R.id.et_pul)
        val et_sle = findViewById<EditText>(R.id.et_sle)
        val et_wor = findViewById<EditText>(R.id.et_wor)
        val et_fun = findViewById<EditText>(R.id.et_fun)
        val et_exe = findViewById<EditText>(R.id.et_exe)
        val btn_update = findViewById<Button>(R.id.btn_update)
        val btn_sync = findViewById<AppCompatButton>(R.id.btn_syn)
        val btn_back = findViewById<AppCompatImageButton>(R.id.btn_back)

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val date = "$year-${month + 1}-$day"

        reference.child(uid).child(date).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
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
                        val glucB = logbook.glucBf
                        val glucBt = logbook.glucBt
                        val glys = logbook.gly
                        val uris = logbook.ur
                        val presB = logbook.presB
                        val pulse = logbook.pulse
                        val sleep = logbook.sleep
                        val execise = logbook.execise
                        val funny = logbook.funny
                        val walk = logbook.walk
                        et_weather.setText(weather)
                        et_airT.setText(airT)
                        et_wTemp.setText(wirT)
                        et_feeling.setText(feeling)
                        et_bre.setText(breakfast)
                        et_lun.setText(lunch)
                        et_din.setText(dinner)
                        et_bT.setText(tempB)
                        et_bP.setText(presB)
                        et_bG.setText(glucB)
                        et_bGt.setText(glucBt)
                        et_gly.setText(glys)
                        et_ur.setText(uris)
                        et_pul.setText(pulse)
                        et_sle.setText(sleep)
                        et_exe.setText(execise)
                        et_fun.setText(funny)
                        et_wor.setText(walk)
                    } else {
                        Toast.makeText(this@LogbookUpdate, "no info", Toast.LENGTH_SHORT).show()
                    }
                }else{

                }
            }
            override fun onCancelled(error: DatabaseError) {

            }


        })

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
            val bgt = et_bGt.text.toString()
            val gly = et_gly.text.toString()
            val uri = et_ur.text.toString()
            val bp = et_bP.text.toString()
            val pul = et_pul.text.toString()
            val sle = et_sle.text.toString()
            val wor = et_wor.text.toString()
            val funny = et_fun.text.toString()
            val exe = et_exe.text.toString()

            val logbook = Logbook(weather,airT,wTemp,feeling,bre,lun,din,bt,bp,bg,bgt,gly,uri,pul,sle,exe,funny,wor)

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
            finish()
        }

        btn_sync.setOnClickListener{
            reference2.child(uid).child(date).addListenerForSingleValueEvent(object :
                ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    if (snapshot.exists()) {
                        val healthTrack = snapshot.getValue(HealthTrack::class.java) as HealthTrack

                        if (healthTrack != null) {

                            val fGlucose = healthTrack.fGlucose
                            val tGlucose = healthTrack.tGlucose
                            val hemoglobin = healthTrack.hemoglobin
                            val urine = healthTrack.urine

                            et_bG.setText(fGlucose.toString())
                            et_bGt.setText(tGlucose.toString())
                            et_gly.setText(hemoglobin.toString())
                            et_ur.setText(urine.toString())
                        } else {
                            Toast.makeText(this@LogbookUpdate, "no info", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(this@LogbookUpdate, "no info", Toast.LENGTH_SHORT).show()
                    }
                }
                override fun onCancelled(error: DatabaseError) {

                }


            })
        }

        btn_back.setOnClickListener {
            val intent = Intent(this, LogbookEdit::class.java)
            startActivity(intent)
            finish()
        }

    }
}