package com.example.goodday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class ArticleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        getSupportActionBar()?.hide()



        val back: ImageButton = findViewById<ImageButton>(R.id.ivReturnArticle)
        back.setOnClickListener {
            val intent = Intent(this@ArticleActivity, NewsInformatinActivity::class.java)
            startActivity(intent)
        }
    }
}