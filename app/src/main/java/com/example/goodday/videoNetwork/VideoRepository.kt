package com.example.goodday.videoNetwork

import androidx.lifecycle.MutableLiveData
import com.example.goodday.videoModel.VideoResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VideoRepository {
    private val videoApi: VideoApi

    fun getVideoActivity(query: String? , orientation:String?, size:String?, locale:String?)
            : MutableLiveData<VideoResponse> {
        val videoData: MutableLiveData<VideoResponse> = MutableLiveData<VideoResponse>()
        videoApi.getVideoListActivity(query,orientation,size,locale)!!.enqueue(object : Callback<VideoResponse?> {
            override fun onResponse(
                call: Call<VideoResponse?>,
                response: Response<VideoResponse?>,
            ) {
                if (response.isSuccessful()) {
                    videoData.setValue(response.body())
                }
            }

            override fun onFailure(call: Call<VideoResponse?>, t: Throwable) {
                videoData.setValue(null)
            }
        })
        return videoData
    }
    fun getVideoFragment()
            : MutableLiveData<VideoResponse> {
        val newsData: MutableLiveData<VideoResponse> = MutableLiveData<VideoResponse>()
        videoApi.getVideoListFragment()!!.enqueue(object : Callback<VideoResponse?> {
            override fun onResponse(
                call: Call<VideoResponse?>,
                response: Response<VideoResponse?>,
            ) {
                if (response.isSuccessful()) {
                    newsData.setValue(response.body())
                }
            }

            override fun onFailure(call: Call<VideoResponse?>, t: Throwable) {
                newsData.setValue(null)
            }
        })
        return newsData
    }


    companion object {
        private var videoRepository: VideoRepository? = null
        fun getInstance(): VideoRepository?
        {
            if (videoRepository == null) {
                videoRepository = VideoRepository()
            }
            return videoRepository
        }
    }

    init {
        videoApi = VideoRetrofitClient.getInstance()?.getApi()!!
    }
}