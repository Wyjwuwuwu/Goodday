package com.example.goodday.newsInfoNetwork

import androidx.lifecycle.MutableLiveData
import com.example.goodday.newsInfoModel.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsRepository {
    private val newsApi: NewsApi

    fun getNewsActivity(searchWord: String?,sortBase: String?, language: String?, source: String?, key: String?)
    : MutableLiveData<NewsResponse> {
        val newsData: MutableLiveData<NewsResponse> = MutableLiveData<NewsResponse>()
        newsApi.getNewsListActivity(searchWord,sortBase, language,source, key)!!.enqueue(object : Callback<NewsResponse?> {
            override fun onResponse(
                call: Call<NewsResponse?>,
                response: Response<NewsResponse?>,
            ) {
                if (response.isSuccessful()) {
                    newsData.setValue(response.body())
                }
            }

            override fun onFailure(call: Call<NewsResponse?>, t: Throwable) {
                newsData.setValue(null)
            }
        })
        return newsData
    }
    fun getNewsFragment(source: String?, key: String?)
            : MutableLiveData<NewsResponse> {
        val newsData: MutableLiveData<NewsResponse> = MutableLiveData<NewsResponse>()
        newsApi.getNewsListFragment(source, key)!!.enqueue(object : Callback<NewsResponse?> {
            override fun onResponse(
                call: Call<NewsResponse?>,
                response: Response<NewsResponse?>,
            ) {
                if (response.isSuccessful()) {
                    newsData.setValue(response.body())
                }
            }

            override fun onFailure(call: Call<NewsResponse?>, t: Throwable) {
                newsData.setValue(null)
            }
        })
        return newsData
    }


    companion object {
        private var newsRepository: NewsRepository? = null
        fun getInstance(): NewsRepository?
             {
                if (newsRepository == null) {
                    newsRepository = NewsRepository()
                }
                return newsRepository
            }
    }

    init {
        newsApi = RetrofitClient.getInstance()?.getApi()!!
    }
}
