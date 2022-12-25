package com.example.goodday.videoModel

import com.google.gson.annotations.SerializedName

data class VideoResponse (
    @SerializedName("image")
    var image: String? = null,

    @SerializedName("url")
    var url: String? = null,

    @SerializedName("videos")
    var videos: List<NewsVideo>? = null
)