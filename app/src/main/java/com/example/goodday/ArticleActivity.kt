package com.example.goodday

import android.app.LauncherActivity.ListItem
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.goodday.databinding.ActivityArticleBinding
import com.example.goodday.newsInfoModel.NewsArticle


class ArticleActivity : AppCompatActivity() {

    private var binding: ActivityArticleBinding? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        getSupportActionBar()?.hide()
//
        val item = intent.getParcelableExtra<NewsArticle>("news")
        if(item!=null)
        {
            binding?.articleTitle?.text = item.title.toString()
            binding?.classification?.text = item.author.toString()
            binding?.date?.text = item.publishedAt.toString()
            binding?.articleContent?.text = item.content.toString()
            binding?.description?.text = item.description.toString()
            Glide.with(this)
                .load(item.urlToImage)
                .apply(RequestOptions.centerCropTransform())
                .into(binding!!.ivImageNews)
        }
        binding?.ivBackHomeArticle?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding?.ivReturnArticle?.setOnClickListener {
            finish()
        }
    }
}