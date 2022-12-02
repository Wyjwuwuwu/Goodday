package com.example.goodday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton

class HealthInfo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_info)

        val btn_back = findViewById<AppCompatImageButton>(R.id.btn_back)
        val btn_article = findViewById<Button>(R.id.btn_article)
        val btn_video = findViewById<Button>(R.id.btn_video)

        btn_back.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

        btn_article.setOnClickListener {
            val intent = Intent(this,NewsInformatinActivity::class.java)
            startActivity(intent)
        }
        btn_video.setOnClickListener {
            val intent = Intent(this,NewsInformatinActivity::class.java)
            startActivity(intent)
        }

    }
}