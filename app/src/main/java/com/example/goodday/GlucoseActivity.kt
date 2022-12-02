package com.example.goodday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class GlucoseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glucose)
        getSupportActionBar()?.hide()
        val button: ImageButton = findViewById(R.id.ivBackHomeHealthCare)
        button.setOnClickListener {
            finish()
        }
        val home: ImageButton = findViewById(R.id.glucoseBackHome)
        home.setOnClickListener {
            val intent = Intent(this@GlucoseActivity, MainActivity::class.java)
            startActivity(intent)
        }

    }
}