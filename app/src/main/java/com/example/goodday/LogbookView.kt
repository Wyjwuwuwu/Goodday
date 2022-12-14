package com.example.goodday

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import com.example.goodday.user.HealthTrack
import com.example.goodday.user.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import java.util.*

class LogbookView : AppCompatActivity() {

    lateinit var user: FirebaseUser
    lateinit var reference: DatabaseReference
    lateinit var reference2: DatabaseReference
    lateinit var uid: String
    lateinit var circularProgressBar: CircularProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logbook_view)

        val btn_track = findViewById<AppCompatButton>(R.id.btn_track)
        val btn_back = findViewById<AppCompatButton>(R.id.btn_back)
        circularProgressBar = findViewById(R.id.circularProgressBar)
        val tv_show = findViewById<TextView>(R.id.tv_show)
        val tv_score = findViewById<TextView>(R.id.tv_score)

        user = FirebaseAuth.getInstance().currentUser!!
        reference = FirebaseDatabase.getInstance().getReference("Users")
        reference2 = FirebaseDatabase.getInstance().getReference("Health_Track")
        uid = user.uid
        val calendar = Calendar.getInstance()
        val currentHourIn24Format: Int =calendar.get(Calendar.HOUR_OF_DAY)

        reference.child(uid).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userProfile = snapshot.getValue(User::class.java) as User

                if (userProfile != null) {
                    val fullname = userProfile.fullName
                    if (currentHourIn24Format in 6..12){
                        tv_show.text = "Good Morning, $fullname"
                    }else if(currentHourIn24Format in 22..5){
                        tv_show.text = "Time for sleep, $fullname"
                    }else if(currentHourIn24Format in 13..17){
                        tv_show.text = "Good Afternoon, $fullname"
                    }else{
                        tv_show.text = "Good Evening, $fullname"
                    }

                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        val date = getdate()
        reference2.child(uid).child(date).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    val health = snapshot.getValue(HealthTrack::class.java) as HealthTrack

                    if(health != null){
                        val healthScore = health.healthScore
                        val score:Float = healthScore!!
                        tv_score.text = score.toString()
                        circle(score)
                    }
                }

            }
            override fun onCancelled(error: DatabaseError) {}
        })



        btn_track.setOnClickListener{
            val intent = Intent(this, LogbookEdit::class.java)
            startActivity(intent)
            finish()
        }

        btn_back.setOnClickListener{
            finish()
        }


    }

    fun getdate(): String {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val date = "$year-${month + 1}-$day"

        return date
    }

    fun circle(score:Float){
        circularProgressBar.apply {
            // Set Progress
            //progress = 85f
            // or with animation
            setProgressWithAnimation(86f, 1000) // =1s

            // Set Progress Max
            progressMax = 100f

            // Set ProgressBar Color
            progressBarColor = Color.rgb(88,139,139)

            // Set background ProgressBar Color
            backgroundProgressBarColor = Color.rgb(245,245,245)

            // Set Width
            progressBarWidth = 18f // in DP
            backgroundProgressBarWidth = 18f // in DP

            // Other
            roundBorder = true
            startAngle = 0f
            progressDirection = CircularProgressBar.ProgressDirection.TO_RIGHT
        }

    }

}