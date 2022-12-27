package com.example.goodday.videoUI

import android.provider.MediaStore.Video
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.goodday.videoModel.VideoResponse
import com.example.goodday.videoNetwork.VideoRepository

class VideoViewModel : ViewModel() {
    private var mutableLiveDataVideo: MutableLiveData<VideoResponse>? = null
    private var videoRepository: VideoRepository? = null
//    private val sources = ""
//    private val API_KEY = "563492ad6f917000010000015e54e27851be44f180ffc8712ee6cd84"
//    6912a61c8aa64e7097a552c45d91c73b
    fun initActivity(query: String? , orientation:String?, size:String?, locale:String?) {
        if (mutableLiveDataVideo != null) {
            return
        }
        videoRepository = VideoRepository.getInstance()
        mutableLiveDataVideo = videoRepository?.getVideoActivity(query,orientation,size,locale)
    }
    fun searchActivity(query: String? , orientation:String?, size:String?, locale:String?) {

        videoRepository = VideoRepository.getInstance()
        mutableLiveDataVideo = videoRepository?.getVideoActivity(query,orientation,size,locale)
    }

    fun initFragment() {
        if (mutableLiveDataVideo != null) {
            return
        }
        videoRepository = VideoRepository.getInstance()
        mutableLiveDataVideo = videoRepository?.getVideoFragment()
    }

    fun getVideoRepository(): LiveData<VideoResponse>? {
        return mutableLiveDataVideo
    }
}
