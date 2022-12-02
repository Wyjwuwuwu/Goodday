package com.example.goodday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class GlucoseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glucose)
        getSupportActionBar()?.hide()
        val button: ImageButton = findViewById(R.id.ivBackHomeHealthCare)
        val ImageView: ImageView = findViewById(R.id.avocado_gif)
        val potato: ImageView = findViewById(R.id.potato_gif)
        button.setOnClickListener {
            finish()
        }
        val home: ImageButton = findViewById(R.id.glucoseBackHome)
        home.setOnClickListener {
            val intent = Intent(this@GlucoseActivity, MainActivity::class.java)
            startActivity(intent)
        }
        //load GIF
        Glide.with(this).load(R.drawable.avocado)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(ImageView)
        Glide.with(this).load(R.drawable.pothos)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(potato)

    }
}