package com.example.goodday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class QuestionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)
        getSupportActionBar()?.hide()
        val ques: ImageButton = findViewById<ImageButton>(R.id.ivBackQuestion)
        val potato_gif: ImageView = findViewById(R.id.potato_gif)
        val oran_gif: ImageView = findViewById(R.id.oran_gif)
        val mushroom_gif: ImageView = findViewById(R.id.mushroom_gif)
        ques.setOnClickListener {
            finish()
        }

        val home: ImageButton = findViewById(R.id.questionBackHome)
        home.setOnClickListener {
            val intent = Intent(this@QuestionActivity, MainActivity::class.java)
            startActivity(intent)
        }
        //load GIF
        Glide.with(this).load(R.drawable.potato)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(potato_gif)
        Glide.with(this).load(R.drawable.orange)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(oran_gif)
        Glide.with(this).load(R.drawable.mushroom)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(mushroom_gif)

    }
}