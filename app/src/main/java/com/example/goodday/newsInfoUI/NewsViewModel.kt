package com.example.goodday.newsInfoUI

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.goodday.newsInfoModel.NewsResponse
import com.example.goodday.newsInfoNetwork.NewsRepository

class NewsViewModel : ViewModel() {
    private var mutableLiveData: MutableLiveData<NewsResponse>? = null
    private var newsRepository: NewsRepository? = null
    private val sources = ""
    private val API_KEY = "6912a61c8aa64e7097a552c45d91c73b"
//    00c772ed92f04fa3a13593f5d0204132
    fun initActivity(searchWord: String?,sortBase: String?,language:String?) {
        if (mutableLiveData != null) {
            return
        }
        newsRepository = NewsRepository.getInstance()
        mutableLiveData = newsRepository?.getNewsActivity(searchWord,sortBase,language,sources, API_KEY)
    }
    fun searchActivity(searchWord: String?,sortBase: String?,language:String?) {

        newsRepository = NewsRepository.getInstance()
        mutableLiveData = newsRepository?.getNewsActivity(searchWord,sortBase,language,sources, API_KEY)
    }

    fun initFragment() {
        if (mutableLiveData != null) {
            return
        }
        newsRepository = NewsRepository.getInstance()
        mutableLiveData = newsRepository?.getNewsFragment(sources, API_KEY)
    }

    fun getNewsRepository(): LiveData<NewsResponse>? {
        return mutableLiveData
    }
}
