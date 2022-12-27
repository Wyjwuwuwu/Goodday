package com.example.goodday

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageButton
import com.example.goodday.adapter.ResultAdapter
import com.example.goodday.user.HealthTrack
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import java.util.*


class HealthView : AppCompatActivity() {

    lateinit var share: AppCompatImageButton
    lateinit var back: AppCompatImageButton
    lateinit var text: TextView
    lateinit var user: FirebaseUser
    lateinit var reference: DatabaseReference
    lateinit var listView: ListView
    lateinit var listView2: ListView
    val arrayList = ArrayList<String>()
    val arrayList2 = ArrayList<String>()
    val imgList = ArrayList<Int>()
    val imgList2 = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_view)

        share = findViewById(R.id.btn_share)
        back = findViewById(R.id.btn_back)
        val btn_treat = findViewById<AppCompatImageButton>(R.id.btn_treat)
        val notice1 = findViewById<TextView>(R.id.tv_no1)
        val notice2 = findViewById<TextView>(R.id.tv_no2)
        val tv_score1 = findViewById<TextView>(R.id.tv_score1)
        val tv_score2 = findViewById<TextView>(R.id.tv_score2)
        reference = FirebaseDatabase.getInstance().getReference("Health_Track")
        listView = findViewById(R.id.listview)
        listView2 = findViewById(R.id.listview2)
        user = FirebaseAuth.getInstance().currentUser!!
        val uid = user.uid

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val date = "$year-${month + 1}-$day"
        val date_p = "$year-${month + 1}-${day - 1}"

        reference.child(uid).child(date).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    val track = snapshot.getValue(HealthTrack::class.java) as HealthTrack

                    if (track != null) {
                        val fGlucose = track.fGlucose
                        val tGlucose = track.tGlucose
                        val hemoglobin = track.hemoglobin
                        val urine = track.urine
                        val score = track.healthScore
                        var ipath_fGlucose = R.drawable.circle_green
                        var ipath_tGlucose = R.drawable.circle_green
                        var ipath_hemoglobin = R.drawable.circle_green
                        var ipath_urine = R.drawable.circle_green

                        tv_score1.text = "Health Score = $score"
                        if (fGlucose != null) {
                            ipath_fGlucose = if (fGlucose <=6.1 ){
                                R.drawable.circle_green
                            }else if (fGlucose<=7 && fGlucose>6.1){
                                R.drawable.circle_ora
                            }else{
                                R.drawable.circle_blue
                            }
                        }
                        if (tGlucose != null) {
                            ipath_tGlucose = if (tGlucose<=7.8){
                                R.drawable.circle_green
                            }else if (tGlucose<=11.1 && tGlucose>7.8){
                                R.drawable.circle_ora
                            }else{
                                R.drawable.circle_blue
                            }
                        }
                        if (hemoglobin != null) {
                            ipath_hemoglobin = if (hemoglobin in 4.0..5.6){
                                R.drawable.circle_green
                            }else if (hemoglobin in 5.7..6.4){
                                R.drawable.circle_ora
                            }else{
                                R.drawable.circle_blue
                            }
                        }
                        if (urine != null) {
                            ipath_urine = if (urine<0.8&&urine>0){
                                R.drawable.circle_green
                            }else {
                                R.drawable.circle_ora
                            }
                        }
                        arrayList.add("Blood Glucose Level (Fasting state) is "
                                + fGlucose.toString()
                                +" mm Hg "+ date)
                        arrayList.add("Blood Glucose Level (2 h after meal) is "
                                + tGlucose.toString()
                                +" mm Hg "+ date)
                        arrayList.add("Glycosylated Hemoglobin Value is "
                                + hemoglobin.toString()
                                +" % "+ date)
                        arrayList.add("Urine Ket Purity is "
                                + urine.toString()
                                +" "+ date)
                        Log.d("arrayList", arrayList.toString())
                        imgList.add(ipath_fGlucose)
                        imgList.add(ipath_tGlucose)
                        imgList.add(ipath_hemoglobin)
                        imgList.add(ipath_urine)
                        val adapter = ResultAdapter(this@HealthView, arrayList, imgList)
                        listView.adapter = adapter
                    }
                }else{
                    notice1.visibility = View.VISIBLE
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        reference.child(uid).child(date_p).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                val track = snapshot.getValue(HealthTrack::class.java) as HealthTrack

                    if (track != null) {
                        val fGlucose2 = track.fGlucose
                        val tGlucose2 = track.tGlucose
                        val hemoglobin2 = track.hemoglobin
                        val urine2 = track.urine
                        val score2 = track.healthScore
                        var ipath_fGlucose2 = R.drawable.circle_green
                        var ipath_tGlucose2 = R.drawable.circle_green
                        var ipath_hemoglobin2 = R.drawable.circle_green
                        var ipath_urine2 = R.drawable.circle_green
                        tv_score2.text = "Health Score = $score2"
                        if (fGlucose2 != null) {
                            ipath_fGlucose2 = if (fGlucose2 <=6.1 ){
                                R.drawable.circle_green
                            }else if (fGlucose2<=7 && fGlucose2>6.1){
                                R.drawable.circle_ora
                            }else{
                                R.drawable.circle_blue
                            }
                        }
                        if (tGlucose2 != null) {
                            ipath_tGlucose2 = if (tGlucose2<=7.8){
                                R.drawable.circle_green
                            }else if (tGlucose2<=11.1 && tGlucose2>7.8){
                                R.drawable.circle_ora
                            }else{
                                R.drawable.circle_blue
                            }
                        }
                        if (hemoglobin2 != null) {
                            ipath_hemoglobin2 = if (hemoglobin2 in 4.0..5.6){
                                R.drawable.circle_green
                            }else if (hemoglobin2 in 5.7..6.4){
                                R.drawable.circle_ora
                            }else{
                                R.drawable.circle_blue
                            }
                        }
                        if (urine2 != null) {
                            ipath_urine2 = if (urine2<0.8&&urine2>0){
                                R.drawable.circle_green
                            }else {
                                R.drawable.circle_ora
                            }
                        }
                        arrayList2.add("Blood Glucose Level (Fasting state) is "
                                + fGlucose2.toString()
                                +" mm Hg "+ date_p)
                        arrayList2.add("Blood Glucose Level (2 h after meal) is "
                                + tGlucose2.toString()
                                +" mm Hg "+ date_p)
                        arrayList2.add("Glycosylated Hemoglobin Value is "
                                + hemoglobin2.toString()
                                +" % "+ date_p)
                        arrayList2.add("Urine Ket Purity is "
                                + urine2.toString()
                                +" "+ date_p)
                        Log.d("arrayList", arrayList2.toString())
                        imgList2.add(ipath_fGlucose2)
                        imgList2.add(ipath_tGlucose2)
                        imgList2.add(ipath_hemoglobin2)
                        imgList2.add(ipath_urine2)
                        val adapter = ResultAdapter(this@HealthView, arrayList2, imgList2)
                        listView2.adapter = adapter
                    }
                }else{
                    notice2.visibility = View.VISIBLE
                }


            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })


        btn_treat.setOnClickListener {
            val intent = Intent (this, TreatmentActivity::class.java)
            startActivity(intent)
        }

        back.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                finish()
            }
        })

        share.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                // Now share text only function will be called
                // here we  will be passing the text to share
                shareTextOnly(text.getText().toString())
            }
        })
    }

    private fun shareTextOnly(titlee: String) {

        // The value which we will sending through data via
        // other applications is defined
        // via the Intent.ACTION_SEND
        val intentt = Intent(Intent.ACTION_SEND)

        // setting type of data shared as text
        intentt.type = "text/plain"
        intentt.putExtra(Intent.EXTRA_SUBJECT, "Subject Here")

        // Adding the text to share using putExtra
        intentt.putExtra(Intent.EXTRA_TEXT, titlee)
        startActivity(Intent.createChooser(intentt, "Share Via"))
    }
}