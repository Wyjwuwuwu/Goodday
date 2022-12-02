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
            R.drawable.hc1,R.drawable.hc2
            ,R.drawable.hc3
            ,R.drawable.hc4,R.drawable.hc5
        )
        title_health_news = arrayOf(
            "NF Medical Care",
            "Brighton Healthcare",
            "HEALTH CARE",
            "Sungai Pelek Health Care",
            "Sunway Home Healthcare"
        )
        address_health_news = arrayOf(
            "No.2-G, Jalan Nilam",
            "Persiaran Cyber Point",
            "L6-07-03, penggawa sekyen 2",
            "No.71 Jalan Besar, Sungai Pelek",
            "F-02-11, Sunway GEO Avenue"
        )
        phone_health_news = arrayOf(
            "012-2667449",
            "03-86881898",
            "012-2634149",
            "03-50337988",
            "012-2186549"
        )
        RecyclerView_health_news = view.findViewById<RecyclerView>(R.id.recylerView_health_care_news)
        RecyclerView_health_news.layoutManager = LinearLayoutManager(view.context)
        RecyclerView_health_news.setHasFixedSize(true)
        ArrayList_health_news = arrayListOf<HealthCareModel>()
        getUserdata()
        //Create the image slider
        imageList = ArrayList<Int>()
        imageList = imageList + R.drawable.newspic1
        imageList = imageList + R.drawable.newspic2
        imageList = imageList + R.drawable.newspic3
        imageList = imageList + R.drawable.newspic4
        imageList = imageList + R.drawable.newspic5
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