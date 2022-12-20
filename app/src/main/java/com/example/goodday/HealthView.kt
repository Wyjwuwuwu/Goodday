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
                        val ipath = R.drawable.circle_green
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
                        imgList.add(ipath)
                        imgList.add(ipath)
                        imgList.add(ipath)
                        imgList.add(ipath)
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
                        val fGlucose = track.fGlucose
                        val tGlucose = track.tGlucose
                        val hemoglobin = track.hemoglobin
                        val urine = track.urine
                        val ipath = R.drawable.circle_green
                        arrayList2.add("Blood Glucose Level (Fasting state) is "
                                + fGlucose.toString()
                                +" mm Hg "+ date)
                        arrayList2.add("Blood Glucose Level (2 h after meal) is "
                                + fGlucose.toString()
                                +" mm Hg "+ date)
                        arrayList2.add("Glycosylated Hemoglobin Value is "
                                + fGlucose.toString()
                                +" % "+ date)
                        arrayList2.add("Urine Ket Purity is "
                                + fGlucose.toString()
                                +" "+ date)
                        Log.d("arrayList", arrayList2.toString())
                        imgList2.add(ipath)
                        imgList2.add(ipath)
                        imgList2.add(ipath)
                        imgList2.add(ipath)
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