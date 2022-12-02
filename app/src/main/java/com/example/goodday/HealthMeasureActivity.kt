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
    lateinit var symbol_shiver: Array<Int>
    lateinit var intro_shiver: Array<String>
    lateinit var symbol_blood: Array<Int>
    lateinit var intro_blood: Array<String>
    lateinit var symbol_temp: Array<Int>
    lateinit var intro_temp: Array<String>
    lateinit var symbol_pain: Array<Int>
    lateinit var intro_pain: Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_measure)

        getSupportActionBar()?.hide()

        val back: ImageButton = findViewById<ImageButton>(R.id.ivBackHomeHealthMeasure)
        back.setOnClickListener {
           finish()
        }

        val home:ImageButton = findViewById<ImageButton>(R.id.healthmeasureBackHome)
        home.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
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
        symbol_shiver = arrayOf(
            R.drawable.circle_blue,R.drawable.circle_ora,R.drawable.circle_blue,R.drawable.circle_green,R.drawable.circle_green
        )
        intro_shiver = arrayOf(
            "Shiver level is high 2022-09-01",
            "Shiver level is high 2022-08-24",
            "Shiver level is high 2022-08-18",
            "Shiver level is high 2022-08-01",
            "Shiver level is high 2022-07-28"
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
        symbol_pain = arrayOf(
            R.drawable.circle_green,R.drawable.circle_blue,R.drawable.circle_ora,R.drawable.circle_green,R.drawable.circle_green
        )
        intro_pain  = arrayOf(
            "Pain level is normal 2022-09-01",
            "Pain level is quite high 2022-08-24",
            "Pain level is high 2022-08-18",
            "Pain level is normal 2022-08-01",
            "Pain level is normal 2022-07-28"

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
                intro_shiver[i],symbol_shiver[i],
                intro_temp[i],symbol_temp[i],
                intro_pain[i],symbol_pain[i]
            )
            ArrayList_health_ms.add(healthCare)
        }
        RecyclerView_health_ms.adapter = HealthMSAdapter(ArrayList_health_ms)
    }
}