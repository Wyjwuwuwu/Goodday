package com.example.goodday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.widget.*
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HealthCareActivity : AppCompatActivity() {
    // Recycler view
    private lateinit var RecyclerView_health_care: RecyclerView
    private lateinit var ArrayList_health_care: ArrayList<HealthCareModel>
    lateinit var image_health_care: Array<Int>
    lateinit var title_health_care: Array<String>
    lateinit var address_health_care: Array<String>
    lateinit var phone_health_care: Array<String>

    var views = arrayOf("more than 10k", "more than 5k", "more than 1k")
    var latest = arrayOf("1 day", "3 days", "5 days","1 month","3 months")
    var period = arrayOf("30s", "1min", "3min")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_care)

        getSupportActionBar()?.hide()


        val back: ImageButton = findViewById(R.id.ivBackHomeHealthCare)
        back.setOnClickListener {
            finish()
        }

        // Recycler view
        image_health_care= arrayOf(
            R.drawable.hc1,R.drawable.hc2
            ,R.drawable.hc3
            ,R.drawable.hc4,R.drawable.hc5
        )
        title_health_care = arrayOf(
            "NF Medical Care",
            "Brighton Healthcare",
            "HEALTH CARE",
            "Sungai Pelek Health Care",
            "Sunway Home Healthcare"
        )
        address_health_care = arrayOf(
            "No.2-G, Jalan Nilam",
            "Persiaran Cyber Point",
            "L6-07-03, penggawa sekyen 2",
            "No.71 Jalan Besar, Sungai Pelek",
            "F-02-11, Sunway GEO Avenue"
        )
        phone_health_care = arrayOf(
            "012-2667449",
            "03-86881898",
            "012-2634149",
            "03-50337988",
            "012-2186549"
        )
        RecyclerView_health_care = findViewById<RecyclerView>(R.id.recylerView_health_care)
        RecyclerView_health_care.layoutManager = LinearLayoutManager(this)
        RecyclerView_health_care.setHasFixedSize(true)
        ArrayList_health_care = arrayListOf<HealthCareModel>()
        getUserdata()
        setSpinner()
    }
    private fun getUserdata(){
        for (i in image_health_care.indices){
            val healthCare = HealthCareModel(image_health_care[i],title_health_care[i],address_health_care[i],phone_health_care[i])
            ArrayList_health_care.add(healthCare)
        }
        RecyclerView_health_care.adapter = HealthCareAdapter(ArrayList_health_care)
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
                    Toast.makeText(this@HealthCareActivity,
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
                    Toast.makeText(this@HealthCareActivity,
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
                    Toast.makeText(this@HealthCareActivity,
                        period[position], Toast.LENGTH_SHORT).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    // write code to perform some action
                }
            }
        }

    }

}
