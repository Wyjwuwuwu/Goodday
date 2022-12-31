package com.example.goodday

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.goodday.databinding.ActivityVideoPlayBinding

class VideoPlayActivity : AppCompatActivity() {
    private var binding: ActivityVideoPlayBinding? = null
    var videoImage : String? = null
    var video :String? = null
    var author: String? = null
    var authorLink :String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVideoPlayBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        getSupportActionBar()?.hide()

        val bundle = intent.extras
        videoImage = bundle!!.getString("videoImage", "Default")
        video = bundle!!.getString("video", "Default")
        author = bundle!!.getString("author", "The author is empty")
        authorLink = bundle!!.getString("authorlink", "The link is empty")
        Glide.with(this)
                .load(videoImage)
                .apply(RequestOptions.centerCropTransform())
                .into(binding!!.ivVideo)
        val uri: Uri = Uri.parse(video)
        binding?.videoView?.setVideoURI(uri)
        binding?.author?.text = author.toString()
        binding?.authorlink?.text = authorLink.toString()

        val mediaController = MediaController(this)
        mediaController.setAnchorView(binding?.videoView)

        // on below line we are setting media player
        // for our media controller.
        mediaController.setMediaPlayer(binding?.videoView)

        // on below line we are setting media
        // controller for our video view.
        binding?.videoView?.setMediaController(mediaController)

        // on below line we are
        // simply starting our video view.
        binding?.videoView?.start()
//        val video = intent.getParcelableExtra<NewsVideo>("video")

//        if(video!=null)
//        {
//            binding?.videoTitle?.text = video.user?.description.toString()
//            binding?.authorlink?.text = video.user?.url.toString()
//            Glide.with(this)
//                .load(video.urlToImage)
//                .apply(RequestOptions.centerCropTransform())
//                .into(binding!!.ivVideo)
//
//        }

        binding?.ivBackHomeVideoPlay?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding?.ivReturnVideo?.setOnClickListener {
            finish()
        }


    }
}