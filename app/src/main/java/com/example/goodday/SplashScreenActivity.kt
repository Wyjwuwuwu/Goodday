package com.example.goodday

import android.app.ActivityOptions
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Pair
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val logo = findViewById<ImageView>(R.id.logo)
        val slogan = findViewById<ImageView>(R.id.imageView)
        val gif = findViewById<ImageView>(R.id.move_gif)


        //Initialize top animation
        val animation1 = AnimationUtils.loadAnimation(this, R.anim.top_wave)
        //Start top animation
        logo.animation = animation1

        //Initialize bottom animation
        val animation2 = AnimationUtils.loadAnimation(this, R.anim.bottom_wave)
        //Start bottom animation
        slogan.animation = animation2

        val animation3 = AnimationUtils.loadAnimation(this, R.anim.left_right)
        //Start bottom animation
        gif.animation = animation3

        //load GIF
        Glide.with(this).load(R.drawable.photato_move)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(gif)

        Handler(Looper.getMainLooper()).postDelayed({
            val i = Intent(this@SplashScreenActivity, OpenInterfaceActivity::class.java)
            startActivity(i)
            finish()
        }, 4000) // delay for 3000 milliseconds or 3 seconds

        //hide status bar
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }

    override fun onStop() {
        super.onStop()
        finish()
    }
}