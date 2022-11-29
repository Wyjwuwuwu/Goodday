package com.example.goodday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class TreatmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_treatment)

        getSupportActionBar()?.hide()
        val button: ImageButton = findViewById<ImageButton>(R.id.ivReturnArticle)
        button.setOnClickListener {
            val intent = Intent(this@TreatmentActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}