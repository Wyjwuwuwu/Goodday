package com.example.goodday

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HealthMeasureActivity : AppCompatActivity() {
    // Recycler view
    private lateinit var RecyclerView_health_ms: RecyclerView
    private lateinit var ArrayList_health_ms: ArrayList<HealthMSModel>
    lateinit var symbol_puls: Array<Int>
    lateinit var intro_puls: Array<String>
    lateinit var symbol_glu: Array<Int>
    lateinit var intro_glu: Array<String>
    lateinit var symbol_blood: Array<Int>
    lateinit var intro_blood: Array<String>
    lateinit var symbol_temp: Array<Int>
    lateinit var intro_temp: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_measure)

        getSupportActionBar()?.hide()
        // Recycler view
        symbol_blood = arrayOf(
            R.drawable.pic2,R.drawable.pic2,R.drawable.pic2,R.drawable.pic2,R.drawable.pic2
        )
        intro_blood = arrayOf(
            "title1","title2","title3","title4","title5"
        )
        symbol_glu = arrayOf(
            R.drawable.pic2,R.drawable.pic2,R.drawable.pic2,R.drawable.pic2,R.drawable.pic2
        )
        intro_glu = arrayOf(
            "title1","title2","title3","title4","title5"
        )
        symbol_puls = arrayOf(
            R.drawable.pic2,R.drawable.pic2,R.drawable.pic2,R.drawable.pic2,R.drawable.pic2
        )
        intro_puls = arrayOf(
            "title1","title2","title3","title4","title5"
        )
        symbol_temp = arrayOf(
            R.drawable.pic2,R.drawable.pic2,R.drawable.pic2,R.drawable.pic2,R.drawable.pic2
        )
        intro_temp = arrayOf(
            "title1","title2","title3","title4","title5"
        )
        RecyclerView_health_ms = findViewById<RecyclerView>(R.id.recylerView_health_measurement)
        RecyclerView_health_ms.layoutManager = LinearLayoutManager(this)
        RecyclerView_health_ms.setHasFixedSize(true)
        ArrayList_health_ms = arrayListOf<HealthMSModel>()
        getUserdata()
    }
    private fun getUserdata(){
        for (i in symbol_blood.indices){
            val healthCare = HealthMSModel(
                intro_puls[i],symbol_puls[i],
                intro_blood[i],symbol_blood[i],
                intro_glu[i],symbol_glu[i],
                intro_temp[i],symbol_temp[i]
            )
            ArrayList_health_ms.add(healthCare)
        }
        RecyclerView_health_ms.adapter = HealthMSAdapter(ArrayList_health_ms)
    }
}