package com.example.goodday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
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

        val back: ImageButton = findViewById<ImageButton>(R.id.ivBackHomeHealthMeasure)
        back.setOnClickListener {
           finish()
        }
        // Recycler view
        symbol_blood = arrayOf(
            R.drawable.circle_green,R.drawable.circle_ora,R.drawable.circle_blue,R.drawable.circle_green,R.drawable.circle_green
        )
        intro_blood = arrayOf(
            "Blood Presure is normal 2022-09-01",
            "Blood Presure is quite high 2022-08-24",
            "Blood Presure is high 2022-08-18",
            "Blood Presure is normal 2022-08-01",
            "Blood Presure is normal 2022-07-28"

        )
        symbol_glu = arrayOf(
            R.drawable.circle_blue,R.drawable.circle_ora,R.drawable.circle_blue,R.drawable.circle_green,R.drawable.circle_green
        )
        intro_glu = arrayOf(
            "Blood glucose level is high 2022-09-01",
            "Blood glucose level is high 2022-08-24",
            "Blood glucose level is high 2022-08-18",
            "Blood glucose level is high 2022-08-01",
            "Blood glucose level is high 2022-07-28"
        )
        symbol_puls = arrayOf(
            R.drawable.circle_ora,R.drawable.circle_ora,R.drawable.circle_blue,R.drawable.circle_green,R.drawable.circle_green
        )
        intro_puls = arrayOf(
            "Pulse Rate is a bit slow 2022-09-01",
            "Pulse Rate is a bit slow 2022-09-01",
            "Pulse Rate is a bit slow 2022-09-01",
            "Pulse Rate is a bit slow 2022-09-01",
            "Pulse Rate is a bit slow 2022-09-01"
        )
        symbol_temp = arrayOf(
            R.drawable.circle_green,R.drawable.circle_blue,R.drawable.circle_ora,R.drawable.circle_green,R.drawable.circle_green
        )
        intro_temp = arrayOf(
            "Temperature is normal 2022-09-01",
            "Temperature is quite high 2022-08-24",
            "Temperature is high 2022-08-18",
            "Temperature is normal 2022-08-01",
            "Temperature is normal 2022-07-28"

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