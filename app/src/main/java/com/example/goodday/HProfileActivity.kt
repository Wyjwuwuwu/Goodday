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
            val intent = Intent(this@HProfileActivity, QuestionActivity::class.java)
            startActivity(intent)
        }
        val back: ImageButton = findViewById<ImageButton>(R.id.ivBackHomeHProfile)
        back.setOnClickListener {
            val intent = Intent(this@HProfileActivity, MainActivity::class.java)
            startActivity(intent)
        }
        val submit: ImageButton = findViewById<ImageButton>(R.id.submit_hprofile)
        submit.setOnClickListener{
            val intent = Intent(this@HProfileActivity, HealthMeasureActivity::class.java)
            startActivity(intent)
        }

    }
}