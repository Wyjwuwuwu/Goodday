package com.example.goodday

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton


class HealthView : AppCompatActivity() {

    lateinit var share: AppCompatImageButton
    lateinit var back: AppCompatImageButton
    lateinit var text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_view)

        share = findViewById(R.id.btn_share)
        text = findViewById(R.id.text)
        back = findViewById(R.id.btn_back)
        val btn_treat = findViewById<AppCompatImageButton>(R.id.btn_treat)

        btn_treat.setOnClickListener {
            val intent = Intent (this, MainActivity::class.java)
            startActivity(intent)
        }

        back.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                finish()
            }
        })

        share.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                // Now share text only function will be called
                // here we  will be passing the text to share
                shareTextOnly(text.getText().toString())
            }
        })
    }

    private fun shareTextOnly(titlee: String) {

        // The value which we will sending through data via
        // other applications is defined
        // via the Intent.ACTION_SEND
        val intentt = Intent(Intent.ACTION_SEND)

        // setting type of data shared as text
        intentt.type = "text/plain"
        intentt.putExtra(Intent.EXTRA_SUBJECT, "Subject Here")

        // Adding the text to share using putExtra
        intentt.putExtra(Intent.EXTRA_TEXT, titlee)
        startActivity(Intent.createChooser(intentt, "Share Via"))
    }
}