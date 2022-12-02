package com.example.goodday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class QuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        getSupportActionBar()?.hide()
        val ques: ImageButton = findViewById<ImageButton>(R.id.ivBackQuestion)
        ques.setOnClickListener {
            finish()
        }

        val home: ImageButton = findViewById(R.id.questionBackHome)
        home.setOnClickListener {
            val intent = Intent(this@QuestionActivity, MainActivity::class.java)
            startActivity(intent)
        }

    }
}