package com.example.goodday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class NewsInformatinActivity : AppCompatActivity() {

    // Recycler view
    private lateinit var RecyclerView_news_information: RecyclerView
    private lateinit var ArrayList_news_information: ArrayList<NewsModel>
    lateinit var imageId_news_information: Array<Int>
    lateinit var heading_news_information: Array<String>
    lateinit var author_news_information: Array<String>

    var views = arrayOf("more than 10k", "more than 5k", "more than 1k")
    var latest = arrayOf("1 day", "3 days", "5 days","1 month","3 months")
    var period = arrayOf("30s", "1min", "3min")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_informatin)

        getSupportActionBar()?.hide()

        setSpinner()


        // Recycler view
        imageId_news_information = arrayOf(
            R.drawable.pic2,R.drawable.pic2,R.drawable.pic2,R.drawable.pic2,R.drawable.pic2
        )
        heading_news_information = arrayOf(
            "title1","title2","title3","title4","title5"
        )
        author_news_information = arrayOf(
            "author1","author2","author3","author4","author5"
        )
        RecyclerView_news_information = findViewById<RecyclerView>(R.id.recylerView_news_information)
        RecyclerView_news_information.layoutManager = LinearLayoutManager(this)
        RecyclerView_news_information.setHasFixedSize(true)
        ArrayList_news_information = arrayListOf<NewsModel>()
        getUserdata()
    }
    private fun getUserdata(){
        for (i in imageId_news_information.indices){
            val news = NewsModel(imageId_news_information[i],heading_news_information[i],author_news_information[i])
            ArrayList_news_information.add(news)
        }
        RecyclerView_news_information.adapter = NewsAdapter(ArrayList_news_information)
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
                    Toast.makeText(this@NewsInformatinActivity,
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
                    Toast.makeText(this@NewsInformatinActivity,
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
                    Toast.makeText(this@NewsInformatinActivity,
                        period[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

    }
}