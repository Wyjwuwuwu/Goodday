package com.example.goodday

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.example.goodday.databinding.FragmentNewsPicTwoBinding
import com.example.goodday.videoModel.NewsVideo
import com.example.goodday.videoUI.VideoAdapter
import com.example.goodday.videoUI.VideoViewModel

class NewsPicTwoFragment : Fragment() {

    //Create the image slider
    private lateinit var viewPager_pic_two: ViewPager
    lateinit var viewPagerAdapter: ImageSliderAdapter
//    private lateinit var vidImageAdapter: VidImageAdapter
    lateinit var imageList: List<Int>

    private var binding: FragmentNewsPicTwoBinding? = null
    var videoArrayList: ArrayList<NewsVideo> = ArrayList<NewsVideo>()
    var videoAdapter: VideoAdapter? = null
    private val videoViewModel: VideoViewModel by viewModels()
    // Recycler view
//    private lateinit var VidImageRV: RecyclerView
//    private lateinit var VidImageList: ArrayList<VidImageModel>
//    lateinit var image: Array<Int>
//    lateinit var imageName: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsPicTwoBinding.inflate(layoutInflater)
        return binding!!.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val video: Button = view.findViewById<Button>(R.id.seeMoreVideo)
        video.setOnClickListener {
            val intent = Intent(view.context, VideoActivity::class.java)
            startActivity(intent)
        }
        videoViewModel!!.initFragment()
        videoViewModel!!.getVideoRepository()?.observe(viewLifecycleOwner) { videoResponse ->
            val newsVideos: List<NewsVideo> = videoResponse.videos!!
            videoArrayList.addAll(newsVideos)
            videoAdapter!!.notifyDataSetChanged()
        }
        setupRecyclerView()
        // Recycler view
//        image = arrayOf(
//            R.drawable.sport1,R.drawable.sport2,R.drawable.sport3,R.drawable.sport4,R.drawable.sport5
//        )
//        imageName = arrayOf(
//            "Legal,ethical concerns of involving children in medical decision",
//            "Health Hacks, things you need to know",
//            "The best health article",
//            "SUMMARY Of health","Flat world health day poster"
//        )
//
//        VidImageRV = view.findViewById(R.id.recylerView)
//        val layoutManager = GridLayoutManager(view.context, 2)
//        VidImageRV.layoutManager = layoutManager
//
//        VidImageList = arrayListOf<VidImageModel>()
//
//        getUserdata()

        //Create the image slider
        imageList = ArrayList<Int>()
        imageList = imageList + R.drawable.sport3
        imageList = imageList + R.drawable.sport4
        imageList = imageList + R.drawable.sport2
        imageList = imageList + R.drawable.sport5
        imageList = imageList + R.drawable.sport1

        viewPagerAdapter = ImageSliderAdapter(view.context,imageList)
        viewPager_pic_two = view.findViewById<ViewPager>(R.id.idViewPager)
        viewPager_pic_two.adapter = viewPagerAdapter
    }
    private fun setupRecyclerView() {
        if (videoAdapter == null) {
            videoAdapter = view?.let { VideoAdapter(it.context, videoArrayList) }
            binding?.recylerView?.layoutManager = GridLayoutManager(requireContext(), 2)
            binding?.recylerView?.adapter = videoAdapter
            binding?.recylerView?.itemAnimator = DefaultItemAnimator()
            binding?.recylerView?.isNestedScrollingEnabled = true
            videoAdapter!!.onItemClick={
                val intent = Intent(context,VideoPlayActivity::class.java)
                val bundle = Bundle()
                bundle.putString("videoImage", it.urlToImage)
                bundle.putString("video", it.videoFile?.get(0)?.url)
                bundle.putString("author", it.user?.description)
                bundle.putString("authorlink", it.user?.url)
                intent.putExtras(bundle)
                startActivity(intent)
            }
        } else {
            videoAdapter!!.notifyDataSetChanged()
        }
    }
//    private fun getUserdata(){
//        for (i in image.indices){
//            val vidImage = VidImageModel(imageName[i],image[i])
//            VidImageList.add(vidImage)
//        }
//        vidImageAdapter= VidImageAdapter(VidImageList)
//        VidImageRV.adapter = vidImageAdapter
//        vidImageAdapter.onItemClick={
//            val intent = Intent(context,VideoPlayActivity::class.java)
//            intent.putExtra("video",it)
//            startActivity(intent)
//        }
//    }

}