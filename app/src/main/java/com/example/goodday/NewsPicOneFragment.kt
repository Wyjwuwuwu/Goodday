package com.example.goodday

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager


class NewsPicOneFragment : Fragment() {


    //Create the image slider
    private lateinit var viewPager_pic_one: ViewPager
    lateinit var viewPagerAdapter: ImageSliderAdapter
    lateinit var imageList: List<Int>

    // Recycler view
    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<NewsModel>
    lateinit var imageId: Array<Int>
    lateinit var heading: Array<String>
    lateinit var author: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_pic_one, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Recycler view
        imageId = arrayOf(
            R.drawable.pic2,R.drawable.pic2,R.drawable.pic2,R.drawable.pic2,R.drawable.pic2
        )
        heading = arrayOf(
            "title1","title2","title3","title4","title5"
        )
        author = arrayOf(
            "author1","author2","author3","author4","author5"
        )
        newRecyclerView = view.findViewById(R.id.recylerView)
        newRecyclerView.layoutManager = LinearLayoutManager(view.context)
        newRecyclerView.setHasFixedSize(true)
        newArrayList = arrayListOf<NewsModel>()
        getUserdata()



        //Create the image slider
        imageList = ArrayList<Int>()
        imageList = imageList + R.drawable.pic2
        imageList = imageList + R.drawable.pic1
        imageList = imageList + R.drawable.pic2
        imageList = imageList + R.drawable.pic1
        imageList = imageList + R.drawable.pic2
        viewPagerAdapter = ImageSliderAdapter(view.context,imageList)
        viewPager_pic_one = view.findViewById<ViewPager>(R.id.idViewPager)
        viewPager_pic_one.adapter = viewPagerAdapter
    }
    private fun getUserdata(){
        for (i in imageId.indices){
            val news = NewsModel(imageId[i],heading[i],author[i])
            newArrayList.add(news)
        }
        newRecyclerView.adapter = NewsAdapter(newArrayList)
    }

}