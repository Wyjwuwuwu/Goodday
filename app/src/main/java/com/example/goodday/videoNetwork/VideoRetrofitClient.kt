package com.example.goodday.videoNetwork

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://api.pexels.com/"

class VideoRetrofitClient {

    companion object {
        private var instance: VideoRetrofitClient? = null
        private var api: VideoApi? = null
        @Synchronized
        fun getInstance(): VideoRetrofitClient? {
            if (instance == null) {
                instance = VideoRetrofitClient()
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    //.client(OkHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                api = retrofit.create(VideoApi::class.java)
            }
            return instance
        }
    }

    fun getApi(): VideoApi? {
        return api
    }
}