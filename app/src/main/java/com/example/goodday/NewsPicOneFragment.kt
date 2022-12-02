package com.example.goodday

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager


class NewsPicOneFragment : Fragment() {


    //Create the image slider
    private lateinit var viewPager_pic_one: ViewPager
    lateinit var viewPagerAdapter: ImageSliderAdapter
    private lateinit var newsAdapter: NewsAdapter
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

        val news: Button = view.findViewById<Button>(R.id.seeMoreArticle)
        news.setOnClickListener {
            val intent = Intent(view.context, NewsInformatinActivity::class.java)
            startActivity(intent)
        }



        // Recycler view
        imageId = arrayOf(
            R.drawable.newspic1,R.drawable.newspic2,R.drawable.newspic3,R.drawable.newspic4,R.drawable.newspic5
        )
        heading = arrayOf(
            "Legal,ethical concerns of involving children in medical decision",
            "Health Hacks, things you need to know",
            "The best health article",
            "SUMMARY Of health","Flat world health day poster"
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
        imageList = imageList + R.drawable.healthdiet1
        imageList = imageList + R.drawable.healthdiet2
        imageList = imageList + R.drawable.healthdiet3
        imageList = imageList + R.drawable.healthdiet4
        imageList = imageList + R.drawable.healthdiet5
        viewPagerAdapter = ImageSliderAdapter(view.context,imageList)
        viewPager_pic_one = view.findViewById<ViewPager>(R.id.idViewPager)
        viewPager_pic_one.adapter = viewPagerAdapter
    }
    private fun getUserdata(){
        for (i in imageId.indices){
            val news = NewsModel(imageId[i],heading[i],author[i])
            newArrayList.add(news)
        }
        newsAdapter= NewsAdapter(newArrayList)
        newRecyclerView.adapter = newsAdapter
        newsAdapter.onItemClick={
            val intent = Intent(context,ArticleActivity::class.java)
            intent.putExtra("news",it)
            startActivity(intent)
        }
    }

}