package com.example.goodday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class HProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hprofile)

        getSupportActionBar()?.hide()
    }
}