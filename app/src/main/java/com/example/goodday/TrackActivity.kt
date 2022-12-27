package com.example.goodday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.example.goodday.user.HealthTrack
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import java.util.*

class TrackActivity : AppCompatActivity() {

    lateinit var uid: String
    lateinit var et_fGlucose: EditText
    lateinit var et_tGlucose: EditText
    lateinit var et_hemoglobin: EditText
    lateinit var et_urine: EditText
    lateinit var user: FirebaseUser
//    var fGlucose : Int? = null
//    var tGlucose : Int? = null
//    var hemoglobin : Int? = null
//    var urine : Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track)

        et_fGlucose = findViewById(R.id.et_fBlood)
        et_tGlucose = findViewById(R.id.et_tBlood)
        et_hemoglobin = findViewById(R.id.et_hHemoglobin)
        et_urine = findViewById(R.id.et_urine)

        user = FirebaseAuth.getInstance().currentUser!!
        uid = user.uid

        getSupportActionBar()?.hide()
        val button: ImageButton = findViewById<ImageButton>(R.id.ivBackHomeTrack)
        button.setOnClickListener {
            finish()
        }
        val ques: ImageButton = findViewById<ImageButton>(R.id.question_track)
        ques.setOnClickListener {
            val intent = Intent(this@TrackActivity, GlucoseActivity::class.java)
            startActivity(intent)
        }
        val track: ImageButton = findViewById<ImageButton>(R.id.submit_track)
        track.setOnClickListener {
            val fGlucose: Float = et_fGlucose.text.toString().toFloat()
            val tGlucose: Float = et_tGlucose.text.toString().toFloat()
            val hemoglobin: Float = et_hemoglobin.text.toString().toFloat()
            val urine: Float = et_urine.text.toString().toFloat()
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val date = "$year-${month + 1}-$day"


            var healthScore = 86.0F

            healthScore = score(fGlucose,tGlucose,hemoglobin,urine).toFloat()
            val healthTrack = HealthTrack(uid, healthScore,fGlucose, tGlucose, hemoglobin, urine,healthScore)
            FirebaseDatabase.getInstance().reference.child("Health_Track")
                .child(FirebaseAuth.getInstance().currentUser!!.uid).child(date)
                .setValue(healthTrack).addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "Submit successfully", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(this, "Submit failed", Toast.LENGTH_SHORT).show()
                    }
                }

            val intent = Intent(this@TrackActivity, HealthView::class.java)
            startActivity(intent)
        }

    }
    fun score(fGlucose:Float,tGlucose:Float,hemoglobin:Float,urine:Float):Int{
        var grade = 0
        if(fGlucose<=6.1){
            grade+=25
        }else if(fGlucose<=7 &&(fGlucose>6.1)){
            grade+=15
        }else if(fGlucose>7){
            grade+=5
        }
        if(tGlucose<=7.8){
            grade+=25
        }else if(tGlucose<=11.1 &&(tGlucose>7.8)){
            grade+=15
        }else if(tGlucose>11.1){
            grade+=5
        }
        if(hemoglobin<=5.6&&hemoglobin>=4){
            grade+=25
        }else if(hemoglobin<=6.4&&hemoglobin>=5.7){
            grade+=15
        }else if(tGlucose>6.4){
            grade+=5
        }
        if(urine<0.8&&urine>0){
            grade+=25
        }else{
            grade+=10
        }
        return grade

    }
}