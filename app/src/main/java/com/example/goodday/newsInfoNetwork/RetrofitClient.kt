package com.example.goodday.newsInfoNetwork

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://newsapi.org/v2/"

class RetrofitClient {

    companion object {
        private var instance: RetrofitClient? = null
        private var api: NewsApi? = null
        @Synchronized
        fun getInstance(): RetrofitClient? {
            if (instance == null) {
                instance = RetrofitClient()
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    //.client(OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                api = retrofit.create(NewsApi::class.java)
            }
            return instance
        }
    }

    fun getApi(): NewsApi? {
        return api
    }
}