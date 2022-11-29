package com.example.goodday

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import com.example.goodday.databinding.ActivityLoginBinding

class ChatListActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_list)

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

        //go to chat interface
        val btnChatListActivityChatInterfaceActivity: View = findViewById<TextView>(R.id.annatext)
        btnChatListActivityChatInterfaceActivity.setOnClickListener {
            //explicit intent
            val intent: Intent = Intent(
                this@ChatListActivity,
                ChatInterfaceActivity::class.java
            )

            startActivity(intent)
        }


    }
}