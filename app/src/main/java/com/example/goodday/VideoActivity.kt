package com.example.goodday

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.goodday.databinding.ActivityNewsInformatinBinding
import com.example.goodday.databinding.ActivityVideoBinding
import com.example.goodday.newsInfoModel.NewsArticle
import com.example.goodday.newsInfoUI.NewsAdapter
import com.example.goodday.videoModel.NewsVideo
import com.example.goodday.videoModel.VideoResponse
import com.example.goodday.videoUI.VideoAdapter
import com.example.goodday.videoUI.VideoViewModel

class VideoActivity : AppCompatActivity() {

    private var binding: ActivityVideoBinding? = null
    var vidArrayList: ArrayList<NewsVideo> = ArrayList<NewsVideo>()
    var videoAdapter: VideoAdapter? = null
    private val videoViewModel: VideoViewModel by viewModels()

    var searchWord: String? = "health"
    var orientation: String?= ""
    var size: String? = ""
    var locale: String? = ""

    var orientationSelect = arrayOf("All","landscape","portrait", "square")
    var sizeSelect = arrayOf("All","large(4K)","medium(Full HD)", "small(HD)")
    var localeSelect = arrayOf("All","en-US", "pt-BR", "es-ES", "de-DE", "it-IT", "fr-FR",
        "sv-SE", "id-ID", "pl-PL", "ja-JP", "zh-CN", "ko-KR", "th-TH")

    //    private lateinit var VidImageRV: RecyclerView
//    private lateinit var VidImageList: ArrayList<VidImageModel>
//    private lateinit var vidImageAdapter: VidImageAdapter
//    lateinit var VidImage: Array<Int>
//    lateinit var VidImageName: Array<String>
//    var views = arrayOf("more than 10k", "more than 5k", "more than 1k")
//    var latest = arrayOf("1 day", "3 days", "5 days","1 month","3 months")
//    var period = arrayOf("30s", "1min", "3min")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_video)

        getSupportActionBar()?.hide()
        binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        videoViewModel!!.initActivity("health","","","")
        videoViewModel!!.getVideoRepository()?.observe(this){
            videoResponse->
            val newsVideo: List<NewsVideo> = videoResponse.videos!!
            vidArrayList.addAll(newsVideo)
            videoAdapter!!.notifyDataSetChanged()
        }

        val back: ImageButton = findViewById<ImageButton>(R.id.ivBackHomevideo)
        back.setOnClickListener {
            finish()
        }
        setupRecyclerView()
        setSpinner()

        binding?.ivSearchButtonVideo?.setOnClickListener {
            setSearch()
        }
    }
    private fun setupRecyclerView() {
        if (videoAdapter == null) {
            updateRecyclerView()
        } else {
            videoAdapter!!.notifyDataSetChanged()
        }
    }
    private fun updateRecyclerView() {
        videoAdapter = VideoAdapter(this@VideoActivity, vidArrayList)
        binding?.idRVCourses?.layoutManager = GridLayoutManager(this, 2)
        binding?.idRVCourses?.adapter = videoAdapter
        binding?.idRVCourses?.itemAnimator = DefaultItemAnimator()
        binding?.idRVCourses?.isNestedScrollingEnabled = true
        videoAdapter!!.onItemClick={
            val bundle = Bundle()
            bundle.putString("videoImage", it.urlToImage)
            bundle.putString("video", it.videoFile?.get(0)?.url)
            bundle.putString("author", it.user?.description)
            bundle.putString("authorlink", it.user?.url)
            val intent = Intent(this,VideoPlayActivity::class.java)
            intent.putExtras(bundle)
            startActivity(intent)
        }
    }
    private fun setSearch(){
        vidArrayList= ArrayList<NewsVideo>()
        searchWord = binding?.svVideoInfo?.text.toString()
        if(searchWord == ""){
            searchWord = "health"
        }
        searchWord = searchWord.toString()
        videoViewModel!!.searchActivity(searchWord,orientation,size,locale)
        videoViewModel!!.getVideoRepository()?.observe(this) { videoResponse ->
            val videoArticles: List<NewsVideo> = videoResponse.videos!!
            vidArrayList.addAll(videoArticles)
            videoAdapter!!.notifyDataSetChanged()
        }
        updateRecyclerView()
        binding?.svVideoInfo?.getText()?.clear()

    }

    private fun setSpinner(){
        //spinner
        val spinner_orientation = findViewById<Spinner>(R.id.spinner_oritation)
        val spinner_size = findViewById<Spinner>(R.id.spinner_view)
        val spinner_locale = findViewById<Spinner>(R.id.spinner_latest)
        if (spinner_orientation != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, orientationSelect)
            spinner_orientation.adapter = adapter

            spinner_orientation.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    vidArrayList= ArrayList<NewsVideo>()
                    orientation = orientationSelect[position]
                    if (orientation.toString() == "All"){
                        orientation = ""
                    }
                    if(size.toString() == "All"){
                        size = ""
                    }
                    if(locale.toString() == "All"){
                        locale = ""
                    }
                    orientation = orientation.toString()

                    Toast.makeText(this@VideoActivity,
                        orientationSelect[position], Toast.LENGTH_SHORT).show()
                    setSearch()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }

            }
        }
        if (spinner_size != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, sizeSelect)
            spinner_size.adapter = adapter

            spinner_size.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    vidArrayList= ArrayList<NewsVideo>()
                    size = sizeSelect[position]
                    if (orientation.toString() == "All"){
                        orientation = ""
                    }
                    if(size.toString() == "All"){
                        size = ""
                    }
                    if(locale.toString() == "All"){
                        locale = ""
                    }
                    size = size.toString()

                    Toast.makeText(this@VideoActivity,
                        sizeSelect[position], Toast.LENGTH_SHORT).show()
                    setSearch()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }

            }
        }
        if (spinner_locale != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item,localeSelect)
            spinner_locale.adapter = adapter

            spinner_locale.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    vidArrayList= ArrayList<NewsVideo>()
                    locale = localeSelect[position]
                    if (locale.toString() == "All"){
                        locale = ""
                    }
                    if(orientation.toString() == "normal"){
                        orientation = ""
                    }
                    if(size.toString() == "normal"){
                        size = ""
                    }
                    locale= locale.toString()

                    Toast.makeText(this@VideoActivity,
                        localeSelect[position], Toast.LENGTH_SHORT).show()
                    setSearch()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }


    }

//    private fun setRecycler(){
//        // Recycler view
//        VidImage = arrayOf(
//            R.drawable.sport1,R.drawable.sport2,R.drawable.sport3,R.drawable.sport4,R.drawable.sport5
//        )
//        VidImageName = arrayOf(
//            "Yoga related health sport introduction",
//            "Check what to do in the gym to loose weight",
//            "Find how many benefits are there when swimming",
//            "What time period is the most suitable for walk and run?",
//            "Dance: A new type of sports nowadays"
//        )
//        VidImageRV = findViewById<RecyclerView>(R.id.idRVCourses)
//        val layoutManager = GridLayoutManager(this, 2)
//        VidImageRV.layoutManager = layoutManager
//
//        VidImageList = arrayListOf<VidImageModel>()
//
//        getUserdata()
//    }
//    private fun getUserdata(){
//        for (i in VidImage.indices){
//            val vidImage = VidImageModel(VidImageName[i],VidImage[i])
//            VidImageList.add(vidImage)
//        }
//        vidImageAdapter= VidImageAdapter(VidImageList)
//        VidImageRV.adapter = vidImageAdapter
//        vidImageAdapter.onItemClick={
//            val intent = Intent(this,VideoPlayActivity::class.java)
//            intent.putExtra("video",it)
//            startActivity(intent)
//        }
//    }
//    private fun setSpinner(){
//        //spinner
//        val spinner_view = findViewById<Spinner>(R.id.spinner_view)
//        val spinner_latest = findViewById<Spinner>(R.id.spinner_latest)
//        val spinner_period = findViewById<Spinner>(R.id.spinner_period)
//        if (spinner_view != null) {
//            val adapter = ArrayAdapter(this,
//                android.R.layout.simple_spinner_item, views)
//            spinner_view.adapter = adapter
//
//            spinner_view.onItemSelectedListener = object :
//                AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(parent: AdapterView<*>,
//                                            view: View, position: Int, id: Long) {
//                    Toast.makeText(this@VideoActivity,
//                        views[position], Toast.LENGTH_SHORT).show()
//                }
//
//                override fun onNothingSelected(parent: AdapterView<*>) {
//                    // write code to perform some action
//                }
//            }
//        }
//        if (spinner_latest != null) {
//            val adapter = ArrayAdapter(this,
//                android.R.layout.simple_spinner_item, latest)
//            spinner_latest.adapter = adapter
//
//            spinner_latest.onItemSelectedListener = object :
//                AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(parent: AdapterView<*>,
//                                            view: View, position: Int, id: Long) {
//                    Toast.makeText(this@VideoActivity,
//                        latest[position], Toast.LENGTH_SHORT).show()
//                }
//
//                override fun onNothingSelected(parent: AdapterView<*>) {
//                    // write code to perform some action
//                }
//            }
//        }
//        if (spinner_period != null) {
//            val adapter = ArrayAdapter(this,
//                android.R.layout.simple_spinner_item, period)
//            spinner_period.adapter = adapter
//
//            spinner_period.onItemSelectedListener = object :
//                AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(parent: AdapterView<*>,
//                                            view: View, position: Int, id: Long) {
//                    Toast.makeText(this@VideoActivity,
//                        period[position], Toast.LENGTH_SHORT).show()
//                }
//
//                override fun onNothingSelected(parent: AdapterView<*>) {
//                    // write code to perform some action
//                }
//            }
//        }
//
//    }

}