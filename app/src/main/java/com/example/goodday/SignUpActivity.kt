package com.example.goodday

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.ImageView
import com.example.goodday.databinding.ActivityLoginBinding
import com.example.goodday.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    var binding: ActivitySignUpBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

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

        //back to main screen
        val btnSignUpActivityOpenInterfaceActivity: View = findViewById<ImageView>(R.id.back)
        btnSignUpActivityOpenInterfaceActivity.setOnClickListener {
            //explicit intent
            val intent: Intent = Intent(
                this@SignUpActivity,
                OpenInterfaceActivity::class.java)

            startActivity(intent)

        }
    }
}