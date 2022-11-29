package com.example.goodday

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.goodday.databinding.ActivityLoginBinding
import com.example.goodday.databinding.ActivityMainBinding

class LoginActivity : AppCompatActivity() {
    //var binding: ActivityLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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
        val btnLoginActivityOpenInterfaceActivity: View = findViewById<ImageView>(R.id.back)
        btnLoginActivityOpenInterfaceActivity.setOnClickListener {
            //explicit intent
            val intent: Intent = Intent(
                this@LoginActivity,
                OpenInterfaceActivity::class.java)

            startActivity(intent)

        }

        //go to forget password screen
        val btnLoginActivityForgetPasswordActivity: View = findViewById<TextView>(R.id.forget_password)
        btnLoginActivityForgetPasswordActivity.setOnClickListener {
            //explicit intent
            val intent: Intent = Intent(
                this@LoginActivity,
                ForgetPasswordActivity::class.java)

            startActivity(intent)

        }
    }
}