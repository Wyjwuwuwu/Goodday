package com.example.goodday.newsInfoModel

import com.google.gson.annotations.SerializedName

class NewsSource (
    var id: String? = null,

    @SerializedName("name")
    var name: String? = null
)
