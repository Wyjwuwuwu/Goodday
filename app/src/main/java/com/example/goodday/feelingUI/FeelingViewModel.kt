package com.example.goodday.feelingUI

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.goodday.feelingModel.Feeling
import com.example.goodday.feelingNetwork.FeelingRepository
import com.example.goodday.newsInfoModel.NewsResponse
import com.example.goodday.newsInfoNetwork.NewsRepository

class FeelingViewModel: ViewModel(){
    private var _feeling: MutableLiveData<ArrayList<Feeling>>? = null
    private var _feeling_latest: MutableLiveData<ArrayList<Feeling>>? = null
    private var feelingRepository: FeelingRepository? = null
    private var latestRepository: FeelingRepository? = null
//    val allFeeling: LiveData<ArrayList<Feeling>>
//        get() = _feeling


    fun initFeeling(){
        getAllFeeling()
        getLatestFeeling()
    }
    fun getLatestFeeling(){
        if(_feeling_latest!=null){
            return
        }
        latestRepository = FeelingRepository.getInstance()
        _feeling_latest = latestRepository?.getLatestFeeling()
    }
    fun getAllFeeling() {

        if (_feeling != null) {
//            Log.d("TAG","get")
            return
        }
        feelingRepository = FeelingRepository.getInstance()
        _feeling = feelingRepository?.getAllFeeling()
    }

    // insert note
    fun addFeeling(id: String, feeling: Feeling) {
        feelingRepository = FeelingRepository.getInstance()
        feelingRepository!!.addFeeling(id, feeling)
    }

    fun getFeelingRepository(): LiveData<ArrayList<Feeling>>? {
        return _feeling
    }
    fun getLatestFeelingRepository(): LiveData<ArrayList<Feeling>>? {
        return _feeling_latest
    }

}
//class FeelingViewModelFactory(private val repository: FeelingRepository)
//    : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(FeelingViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return FeelingViewModel() as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
