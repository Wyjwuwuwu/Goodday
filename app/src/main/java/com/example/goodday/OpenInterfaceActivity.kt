package com.example.goodday

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.goodday.databinding.ActivityMainBinding


class OpenInterfaceActivity : AppCompatActivity() {

    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_interface)

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


        val btnOpenInterfaceActivityLoginActivity: Button = findViewById<Button>(R.id.btn_login_first)
        btnOpenInterfaceActivityLoginActivity.setOnClickListener {
            //explicit intent
            val intent: Intent = Intent(
                this@OpenInterfaceActivity,
                LoginActivity::class.java)

            startActivity(intent)

        }

        val btnOpenInterfaceActivitySignUpActivity: Button = findViewById<Button>(R.id.btn_signup)
        btnOpenInterfaceActivitySignUpActivity.setOnClickListener {
            //explicit intent
            val intent: Intent = Intent(
                this@OpenInterfaceActivity,
                SignUpActivity::class.java
            )

            startActivity(intent)
        }

        val btnOpenInterfaceActivityChatList: Button = findViewById<Button>(R.id.btn_chatlist_)
        btnOpenInterfaceActivityChatList.setOnClickListener {
            //explicit intent
            val intent: Intent = Intent(
                this@OpenInterfaceActivity,
                ChatListActivity::class.java
            )

            startActivity(intent)
        }

        val btnOpenInterfaceActivitySettingActivity: Button = findViewById<Button>(R.id.btn_setting_)
        btnOpenInterfaceActivitySettingActivity.setOnClickListener {
            //explicit intent
            val intent: Intent = Intent(
                this@OpenInterfaceActivity,
                SettingActivity::class.java
            )

            startActivity(intent)
        }

        val btnOpenInterfaceActivityHealthAlertActivity: Button = findViewById<Button>(R.id.btn_alert_)
        btnOpenInterfaceActivityHealthAlertActivity.setOnClickListener {
            //explicit intent
            val intent: Intent = Intent(
                this@OpenInterfaceActivity,
                HealthAlertActivity::class.java
            )

            startActivity(intent)
        }

        val btnOpenInterfaceActivityEditProfileActivity: Button = findViewById<Button>(R.id.btn_editprofile_)
        btnOpenInterfaceActivityEditProfileActivity.setOnClickListener {
            //explicit intent
            val intent: Intent = Intent(
                this@OpenInterfaceActivity,
                EditProfileActivity::class.java
            )

            startActivity(intent)
        }

    }
}