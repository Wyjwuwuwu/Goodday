package com.example.goodday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton

class LogbookView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logbook_view)

        val btn_track = findViewById<AppCompatButton>(R.id.btn_track)
        val btn_back = findViewById<AppCompatButton>(R.id.btn_back)

        btn_track.setOnClickListener{
            val intent = Intent(this, LogbookEdit::class.java)
            startActivity(intent)
        }

        btn_back.setOnClickListener{
            finish()
        }
    }
}