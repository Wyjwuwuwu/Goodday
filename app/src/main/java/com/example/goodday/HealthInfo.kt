package com.example.goodday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton

class HealthInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_info)

        val btn_back = findViewById<AppCompatImageButton>(R.id.btn_back)

        btn_back.setOnClickListener {
            val intent = Intent(this,TreatmentActivity::class.java)
            startActivity(intent)
        }
    }
}