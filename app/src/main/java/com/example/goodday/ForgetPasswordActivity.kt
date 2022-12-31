package com.example.goodday

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageButton

class ForgetPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        //hide action bar
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

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

        val btnForgetPasswordActivityCheckValidationActivity: Button = findViewById<Button>(R.id.btn_send)
        btnForgetPasswordActivityCheckValidationActivity.setOnClickListener {
            //explicit intent
            val intent: Intent = Intent(
                this@ForgetPasswordActivity,
                CheckValidationActivity::class.java
            )

            startActivity(intent)
        }

        val back : View = findViewById<ImageButton>(R.id.back_to)
        back.setOnClickListener{
            finish()
        }
    }
}