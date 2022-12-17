package com.example.goodday.newsInfoNetwork


import com.example.goodday.newsInfoModel.NewsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines?country=my&category=health")
    fun getNewsListFragment(
        @Query("sources") newsSource: String?,
        @Query("apiKey") apiKey: String?,
    ): Call<NewsResponse?>?

    @GET("everything")
    fun getNewsListActivity(
        @Query("q") searchWord: String?,
        @Query("sortBy") sortBase: String?,
        @Query("language") language: String?,
        @Query("sources") newsSource: String?,
        @Query("apiKey") apiKey: String?,
    ): Call<NewsResponse?>?


// https://newsapi.org/v2/top-headlines?sources=google-news&apiKey=6912a61c8aa64e7097a552c45d91c73b
}
