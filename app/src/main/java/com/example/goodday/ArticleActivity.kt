package com.example.goodday

import android.content.Intent
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView

class ArticleActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)
        getSupportActionBar()?.hide()

        val news = intent.getParcelableExtra<NewsModel>("news")
        if(news!=null)
        {
            val textView: TextView = findViewById(R.id.articleTitle)
            val imageView: ImageView = findViewById(R.id.ivImageNews)
            textView.text = news.heading
            imageView.setImageResource(news.titleImage)

        }
        val backHome: ImageButton =findViewById<ImageButton>(R.id.ivBackHomeArticle)
        backHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val back: ImageButton = findViewById<ImageButton>(R.id.ivReturnArticle)
        back.setOnClickListener {
            finish()
        }
    }
}