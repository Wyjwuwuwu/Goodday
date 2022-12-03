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
import android.widget.ImageView
import android.widget.TextView

class ChatInterfaceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_interface)

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

        //back to chat list
//        val btnChatInterfaceChatListActivity: View = findViewById<ImageView>(R.id.chat_back)
//        btnChatInterfaceChatListActivity.setOnClickListener {
//            //explicit intent
//            val intent: Intent = Intent(
//                this@ChatInterfaceActivity,
//                ChatListActivity::class.java
//            )
//
//            startActivity(intent)
//        }

        val back : View = findViewById<ImageButton>(R.id.chat_back)
        back.setOnClickListener{
            finish()
        }
    }
}