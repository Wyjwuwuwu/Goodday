package com.example.goodday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.widget.ImageButton
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_care)

        getSupportActionBar()?.hide()


        val button: ImageButton = findViewById(R.id.ivBackHomeHealthCare)
        button.setOnClickListener {
            
            val intent = Intent(this@HealthCareActivity, MainActivity::class.java)
            startActivity(intent)
        }

        // Recycler view
        image_health_care= arrayOf(
            R.drawable.pic2,R.drawable.pic2,R.drawable.pic2,R.drawable.pic2,R.drawable.pic2
        )
        title_health_care = arrayOf(
            "title1","title2","title3","title4","title5"
        )
        address_health_care = arrayOf(
            "author1","author2","author3","author4","author5"
        )
        phone_health_care = arrayOf(
            "11111","11111","11111","11111","11111"
        )
        RecyclerView_health_care = findViewById<RecyclerView>(R.id.recylerView_health_care)
        RecyclerView_health_care.layoutManager = LinearLayoutManager(this)
        RecyclerView_health_care.setHasFixedSize(true)
        ArrayList_health_care = arrayListOf<HealthCareModel>()
        getUserdata()
    }
    private fun getUserdata(){
        for (i in image_health_care.indices){
            val healthCare = HealthCareModel(image_health_care[i],title_health_care[i],address_health_care[i],phone_health_care[i])
            ArrayList_health_care.add(healthCare)
        }
        RecyclerView_health_care.adapter = HealthCareAdapter(ArrayList_health_care)
    }
}