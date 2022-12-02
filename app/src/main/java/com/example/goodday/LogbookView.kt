package com.example.goodday

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import com.mikhaellopez.circularprogressbar.CircularProgressBar

class LogbookView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logbook_view)

        val btn_track = findViewById<AppCompatButton>(R.id.btn_track)
        val btn_back = findViewById<AppCompatButton>(R.id.btn_back)
        val circularProgressBar = findViewById<CircularProgressBar>(R.id.circularProgressBar)

        btn_track.setOnClickListener{
            val intent = Intent(this, LogbookEdit::class.java)
            startActivity(intent)
        }

        btn_back.setOnClickListener{
            finish()
        }

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