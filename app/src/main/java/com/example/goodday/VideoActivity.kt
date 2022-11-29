package com.example.goodday

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.goodday.databinding.ActivityVideoBinding

class VideoActivity : AppCompatActivity() {
    private lateinit var VidImageRV: RecyclerView
    private lateinit var VidImageList: ArrayList<VidImageModel>

    lateinit var VidImage: Array<Int>
    lateinit var VidImageName: Array<String>
    var views = arrayOf("more than 10k", "more than 5k", "more than 1k")
    var latest = arrayOf("1 day", "3 days", "5 days","1 month","3 months")
    var period = arrayOf("30s", "1min", "3min")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)

        getSupportActionBar()?.hide()

        val back: ImageButton = findViewById<ImageButton>(R.id.ivBackHomevideo)
        back.setOnClickListener {
            val intent = Intent(this@VideoActivity, MainActivity::class.java)
            startActivity(intent)
        }
        setRecycler()
        setSpinner()
    }
    private fun setRecycler(){
        // Recycler view
        VidImage = arrayOf(
            R.drawable.pic2,R.drawable.pic2,R.drawable.pic2,R.drawable.pic2,R.drawable.pic2
        )
        VidImageName = arrayOf(
            "title1","title2","title3","title4","title5"
        )
        VidImageRV = findViewById<RecyclerView>(R.id.idRVCourses)
        val layoutManager = GridLayoutManager(this, 2)
        VidImageRV.layoutManager = layoutManager

        VidImageList = arrayListOf<VidImageModel>()

        getUserdata()
    }
    private fun getUserdata(){
        for (i in VidImage.indices){
            val vidImage = VidImageModel(VidImageName[i],VidImage[i])
            VidImageList.add(vidImage)
        }
        VidImageRV.adapter = VidImageAdapter(VidImageList)
    }
    private fun setSpinner(){
        //spinner
        val spinner_view = findViewById<Spinner>(R.id.spinner_view)
        val spinner_latest = findViewById<Spinner>(R.id.spinner_latest)
        val spinner_period = findViewById<Spinner>(R.id.spinner_period)
        if (spinner_view != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, views)
            spinner_view.adapter = adapter

            spinner_view.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    Toast.makeText(this@VideoActivity,
                        views[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
        if (spinner_latest != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, latest)
            spinner_latest.adapter = adapter

            spinner_latest.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    Toast.makeText(this@VideoActivity,
                        latest[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }
        if (spinner_period != null) {
            val adapter = ArrayAdapter(this,
                android.R.layout.simple_spinner_item, period)
            spinner_period.adapter = adapter

            spinner_period.onItemSelectedListener = object :
                AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>,
                                            view: View, position: Int, id: Long) {
                    Toast.makeText(this@VideoActivity,
                        period[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

    }

}