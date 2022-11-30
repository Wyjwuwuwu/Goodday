package com.example.goodday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton

class LogbookEdit : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_logbook_edit)

        val btn_back = findViewById<AppCompatImageButton>(R.id.btn_back)
        val btn_record = findViewById<AppCompatButton>(R.id.btn_record)

        btn_back.setOnClickListener {
            finish()
        }

        btn_record.setOnClickListener {
            val intent = Intent(this, LogbookEdit::class.java)
            startActivity(intent)
            finish()
        }
    }
}