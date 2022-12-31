package com.example.goodday.videoNetwork


import com.example.goodday.newsInfoModel.NewsResponse
import com.example.goodday.videoModel.VideoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface VideoApi {
    @Headers("Authorization: 563492ad6f917000010000015e54e27851be44f180ffc8712ee6cd84")
    @GET("videos/popular")
    fun getVideoListFragment(
    ): Call<VideoResponse?>?

    @Headers("Authorization: 563492ad6f917000010000015e54e27851be44f180ffc8712ee6cd84")
    @GET("videos/search?")
    fun getVideoListActivity(
        @Query("query") queryParam: String?,
        @Query("orientation") orientation: String?,
        @Query("size") size: String?,
        @Query("locale") locale: String?
    ): Call<VideoResponse?>?


// https://newsapi.org/v2/top-headlines?sources=google-news&apiKey=6912a61c8aa64e7097a552c45d91c73b
}