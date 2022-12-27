package com.example.goodday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.goodday.databinding.ActivityHealthMeasureBinding
import com.example.goodday.feelingModel.Feeling
//import com.example.goodday.feelingModel.bindFeeling
import com.example.goodday.feelingUI.FeelingAdapter
import com.example.goodday.feelingUI.FeelingViewModel

class HealthMeasureActivity : AppCompatActivity() {
    private var binding: ActivityHealthMeasureBinding? = null
    var feelingArrayList: ArrayList<Feeling> = ArrayList<Feeling>()
    var latest: ArrayList<Feeling> = ArrayList<Feeling>()
    var feelingAdapter: FeelingAdapter? = null
    var healthMeasureAdapter:FeelingAdapter? = null


    private val feelingViewModel: FeelingViewModel by viewModels ()
    private val latestModel: FeelingViewModel by viewModels ()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_health_measure)

        getSupportActionBar()?.hide()
        binding = ActivityHealthMeasureBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        latestModel!!.initFeeling()
        latestModel.getLatestFeelingRepository()?.observe(this){
            feeling->
                var feelings: ArrayList<Feeling>? = feeling
                feelings?.let { latest.addAll(it) }
                healthMeasureAdapter!!.notifyDataSetChanged()

            if(latest.isEmpty()){
                binding?.tvNo2?.visibility = View.VISIBLE
            }
        }

        if(healthMeasureAdapter == null){
            healthMeasureAdapter = FeelingAdapter(this@HealthMeasureActivity, latest)
            binding?.recylerViewLatest?.adapter = healthMeasureAdapter
            binding?.recylerViewLatest?.layoutManager = LinearLayoutManager(this)
            binding?.recylerViewLatest?.itemAnimator = DefaultItemAnimator()
            binding?.recylerViewLatest?.isNestedScrollingEnabled = true
        }else {
            feelingAdapter!!.notifyDataSetChanged()
        }
        feelingViewModel.getFeelingRepository()?.observe(this) {
                feeling ->
            val feelings: ArrayList<Feeling>? = feeling
            feelings?.let { feelingArrayList.addAll(it) }
            feelingAdapter!!.notifyDataSetChanged()

            if(feelingArrayList.isEmpty()){
                binding?.tvNo1?.visibility = View.VISIBLE
            }
        }
        if(feelingAdapter == null){

            feelingAdapter = FeelingAdapter(this@HealthMeasureActivity, feelingArrayList)
            binding?.recylerViewHealthMeasurement?.adapter = feelingAdapter
            binding?.recylerViewHealthMeasurement?.layoutManager = LinearLayoutManager(this)
            binding?.recylerViewHealthMeasurement?.itemAnimator = DefaultItemAnimator()
            binding?.recylerViewHealthMeasurement?.isNestedScrollingEnabled = true
        }else {
            feelingAdapter!!.notifyDataSetChanged()
        }

        val back: ImageButton = findViewById<ImageButton>(R.id.ivBackHomeHealthMeasure)
        back.setOnClickListener {
            finish()
        }

        val home:ImageButton = findViewById<ImageButton>(R.id.healthmeasureBackHome)
        home.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


        // Recycler view
//        symbol_blood = arrayOf(
//            R.drawable.circle_green,R.drawable.circle_ora,R.drawable.circle_blue,R.drawable.circle_green,R.drawable.circle_green
//        )
//        intro_blood = arrayOf(
//            "Blood Presure is normal 2022-09-01",
//            "Blood Presure is quite high 2022-08-24",
//            "Blood Presure is high 2022-08-18",
//            "Blood Presure is normal 2022-08-01",
//            "Blood Presure is normal 2022-07-28"
//
//        )
//        symbol_shiver = arrayOf(
//            R.drawable.circle_blue,R.drawable.circle_ora,R.drawable.circle_blue,R.drawable.circle_green,R.drawable.circle_green
//        )
//        intro_shiver = arrayOf(
//            "Shiver level is high 2022-09-01",
//            "Shiver level is high 2022-08-24",
//            "Shiver level is high 2022-08-18",
//            "Shiver level is high 2022-08-01",
//            "Shiver level is high 2022-07-28"
//        )
//        symbol_puls = arrayOf(
//            R.drawable.circle_ora,R.drawable.circle_ora,R.drawable.circle_blue,R.drawable.circle_green,R.drawable.circle_green
//        )
//        intro_puls = arrayOf(
//            "Pulse Rate is a bit slow 2022-09-01",
//            "Pulse Rate is a bit slow 2022-09-01",
//            "Pulse Rate is a bit slow 2022-09-01",
//            "Pulse Rate is a bit slow 2022-09-01",
//            "Pulse Rate is a bit slow 2022-09-01"
//        )
//        symbol_temp = arrayOf(
//            R.drawable.circle_green,R.drawable.circle_blue,R.drawable.circle_ora,R.drawable.circle_green,R.drawable.circle_green
//        )
//        intro_temp = arrayOf(
//            "Temperature is normal 2022-09-01",
//            "Temperature is quite high 2022-08-24",
//            "Temperature is high 2022-08-18",
//            "Temperature is normal 2022-08-01",
//            "Temperature is normal 2022-07-28"
//
//        )
//        symbol_pain = arrayOf(
//            R.drawable.circle_green,R.drawable.circle_blue,R.drawable.circle_ora,R.drawable.circle_green,R.drawable.circle_green
//        )
//        intro_pain  = arrayOf(
//            "Pain level is normal 2022-09-01",
//            "Pain level is quite high 2022-08-24",
//            "Pain level is high 2022-08-18",
//            "Pain level is normal 2022-08-01",
//            "Pain level is normal 2022-07-28"
//
//        )
//        RecyclerView_health_ms = findViewById<RecyclerView>(R.id.recylerView_health_measurement)
//        RecyclerView_health_ms.layoutManager = LinearLayoutManager(this)
//        RecyclerView_health_ms.setHasFixedSize(true)
//        ArrayList_health_ms = arrayListOf<HealthMSModel>()
//        getUserdata()

//    private fun getUserdata(){
//        for (i in symbol_blood.indices){
//            val healthCare = HealthMSModel(
//                intro_puls[i],symbol_puls[i],
//                intro_blood[i],symbol_blood[i],
//                intro_shiver[i],symbol_shiver[i],
//                intro_temp[i],symbol_temp[i],
//                intro_pain[i],symbol_pain[i]
//            )
//            ArrayList_health_ms.add(healthCare)
//        }
//        RecyclerView_health_ms.adapter = HealthMSAdapter(ArrayList_health_ms)
//    }
}