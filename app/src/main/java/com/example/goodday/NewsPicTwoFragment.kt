package com.example.goodday

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager

class NewsPicTwoFragment : Fragment() {

    //Create the image slider
    private lateinit var viewPager_pic_two: ViewPager
    lateinit var viewPagerAdapter: ImageSliderAdapter
    lateinit var imageList: List<Int>

    // Recycler view
    private lateinit var VidImageRV: RecyclerView
    private lateinit var VidImageList: ArrayList<VidImageModel>
    lateinit var image: Array<Int>
    lateinit var imageName: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_news_pic_two, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val video: Button = view.findViewById<Button>(R.id.seeMoreVideo)
        video.setOnClickListener {
            val intent = Intent(view.context, VideoActivity::class.java)
            startActivity(intent)
        }
        // Recycler view
        image = arrayOf(
            R.drawable.pic2,R.drawable.pic2,R.drawable.pic2,R.drawable.pic2,R.drawable.pic2
        )
        imageName = arrayOf(
            "title1","title2","title3","title4","title5"
        )

        VidImageRV = view.findViewById(R.id.recylerView)
        val layoutManager = GridLayoutManager(view.context, 2)
        VidImageRV.layoutManager = layoutManager

        VidImageList = arrayListOf<VidImageModel>()

        getUserdata()

        //Create the image slider
        imageList = ArrayList<Int>()
        imageList = imageList + R.drawable.pic2
        imageList = imageList + R.drawable.pic1
        imageList = imageList + R.drawable.pic2
        imageList = imageList + R.drawable.pic1
        imageList = imageList + R.drawable.pic2
        viewPagerAdapter = ImageSliderAdapter(view.context,imageList)
        viewPager_pic_two = view.findViewById<ViewPager>(R.id.idViewPager)
        viewPager_pic_two.adapter = viewPagerAdapter
    }
    private fun getUserdata(){
        for (i in image.indices){
            val vidImage = VidImageModel(imageName[i],image[i])
            VidImageList.add(vidImage)
        }
        VidImageRV.adapter = VidImageAdapter(VidImageList)
    }

}