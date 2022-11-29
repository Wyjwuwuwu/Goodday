package com.example.goodday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class HProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hprofile)

        getSupportActionBar()?.hide()

        val ques: ImageButton = findViewById<ImageButton>(R.id.question_hprofile)
        ques.setOnClickListener {
            val intent = Intent(this@HProfileActivity, GlucoseActivity::class.java)
            startActivity(intent)
        }
    }
}