package com.example.goodday

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager


class NewsPicThreeFragment : Fragment() {

    //Create the image slider
    private lateinit var viewPager_pic_three: ViewPager
    lateinit var viewPagerAdapter: ImageSliderAdapter
    lateinit var imageList: List<Int>
    // Recycler view
    private lateinit var RecyclerView_health_news: RecyclerView
    private lateinit var ArrayList_health_news: ArrayList<HealthCareModel>
    lateinit var image_health_news: Array<Int>
    lateinit var title_health_news: Array<String>
    lateinit var address_health_news: Array<String>
    lateinit var phone_health_news: Array<String>


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_pic_three, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val health: Button = view.findViewById<Button>(R.id.seeMoreHealth)
        health.setOnClickListener {
            val intent = Intent(view.context, HealthCareActivity::class.java)
            startActivity(intent)
        }
        // Recycler view
        image_health_news= arrayOf(
            R.drawable.pic2,R.drawable.pic2,R.drawable.pic2,R.drawable.pic2,R.drawable.pic2
        )
        title_health_news = arrayOf(
            "title1","title2","title3","title4","title5"
        )
        address_health_news = arrayOf(
            "author1","author2","author3","author4","author5"
        )
        phone_health_news = arrayOf(
            "11111","11111","11111","11111","11111"
        )
        RecyclerView_health_news = view.findViewById<RecyclerView>(R.id.recylerView_health_care_news)
        RecyclerView_health_news.layoutManager = LinearLayoutManager(view.context)
        RecyclerView_health_news.setHasFixedSize(true)
        ArrayList_health_news = arrayListOf<HealthCareModel>()
        getUserdata()
        //Create the image slider
        imageList = ArrayList<Int>()
        imageList = imageList + R.drawable.pic2
        imageList = imageList + R.drawable.pic1
        imageList = imageList + R.drawable.pic2
        imageList = imageList + R.drawable.pic1
        imageList = imageList + R.drawable.pic2
        viewPagerAdapter = ImageSliderAdapter(view.context,imageList)
        viewPager_pic_three = view.findViewById<ViewPager>(R.id.idViewPager_news_three)
        viewPager_pic_three.adapter = viewPagerAdapter
    }
    private fun getUserdata(){
        for (i in image_health_news.indices){
            val healthCare = HealthCareModel(image_health_news[i],title_health_news[i],address_health_news[i],phone_health_news[i])
            ArrayList_health_news.add(healthCare)
        }
        RecyclerView_health_news.adapter = HealthCareAdapter(ArrayList_health_news)
    }


    }