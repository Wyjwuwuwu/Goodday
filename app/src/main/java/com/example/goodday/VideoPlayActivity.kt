package com.example.goodday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class VideoPlayActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_play)

        getSupportActionBar()?.hide()

        val video = intent.getParcelableExtra<VidImageModel>("video")
        if(video!=null)
        {
            val textView: TextView = findViewById(R.id.videoTitle)
            val imageView: ImageView = findViewById(R.id.ivVideo)
            textView.text = video.VidImageName
            imageView.setImageResource(video.VidImageImg)

        }

        val back: ImageButton = findViewById<ImageButton>(R.id.ivReturnVideo)
        back.setOnClickListener {
            finish()
        }
    }
}