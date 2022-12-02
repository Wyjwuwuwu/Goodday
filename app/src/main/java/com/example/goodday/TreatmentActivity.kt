package com.example.goodday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class TreatmentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_treatment)

        getSupportActionBar()?.hide()
        val button: ImageButton = findViewById(R.id.ivReturnArticle)
        val ImageView: ImageView = findViewById(R.id.orange_gif)
        button.setOnClickListener {
            finish()
        }

        //load GIF
        Glide.with(this).load(R.drawable.skate_orange)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(ImageView)
    }
}