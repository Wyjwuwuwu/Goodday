package com.example.goodday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class TrackActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track)

        getSupportActionBar()?.hide()
        val button: ImageButton = findViewById<ImageButton>(R.id.ivBackHomeTrack)
//        button.setOnClickListener {
//            val intent = Intent(this@TrackActivity, NewsFragment::class.java)
//            startActivity(intent)
//        }
        val ques: ImageButton = findViewById<ImageButton>(R.id.question_track)
        ques.setOnClickListener {
            val intent = Intent(this@TrackActivity, GlucoseActivity::class.java)
            startActivity(intent)
        }

    }
}