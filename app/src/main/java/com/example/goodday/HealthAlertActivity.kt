package com.example.goodday

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import com.example.goodday.adapter.AlertAdapter
import com.example.goodday.adapter.ResultAdapter
import com.example.goodday.user.HealthTrack
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import java.util.*

class HealthAlertActivity : AppCompatActivity() {

    lateinit var user: FirebaseUser
    lateinit var reference: DatabaseReference
    lateinit var listView: ListView
    lateinit var listView2: ListView

    val arrayList = ArrayList<String>()
    val arrayList2 = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_health_alert)

        val notice1 = findViewById<TextView>(R.id.tv_no1)
        val notice2 = findViewById<TextView>(R.id.tv_no2)
        reference = FirebaseDatabase.getInstance().getReference("Health_Track")
        listView = findViewById(R.id.listview_beyond)
        listView2 = findViewById(R.id.listview_healthy)
        user = FirebaseAuth.getInstance().currentUser!!
        val uid = user.uid

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val date = "$year-${month + 1}-$day"
        val date_p = "$year-${month + 1}-${day - 1}"

        Log.d("date", date.toString())

        reference.child(uid).child(date).addListenerForSingleValueEvent(object :
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    val track = snapshot.getValue(HealthTrack::class.java) as HealthTrack

                    if (track != null) {
                        val fGlucose = track.fGlucose
                        val tGlucose = track.tGlucose
                        val hemoglobin = track.hemoglobin
                        val urine = track.urine

                        if (fGlucose != null) {
                            if(fGlucose>=7.0){
                                arrayList.add("Blood Glucose Level (Fasting state) is Beyond Standard")
                            }
                        }

                        if (tGlucose != null) {
                            if(tGlucose>=11.1){
                                arrayList.add("Blood Glucose Level (2h after meal) is Beyond Standard")
                            }
                        }

                        if (hemoglobin != null) {
                            if(hemoglobin>=6.5){
                                arrayList.add("Glycosylated Hemoglobin is Beyond Standard")
                            }
                        }

                        if (urine != null) {
                            if(urine>=10){
                                arrayList.add("Urine Ket Purity is Beyond Standard")
                            }
                        }

                        Log.d("arrayList", arrayList.toString())

                        val adapter = AlertAdapter(this@HealthAlertActivity, arrayList)
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

//        reference.child(uid).child(date_p).addListenerForSingleValueEvent(object : ValueEventListener {
//
//            override fun onDataChange(snapshot: DataSnapshot) {
//                if(snapshot.exists()){
//                    val track = snapshot.getValue(HealthTrack::class.java) as HealthTrack
//
//                    if (track != null) {
//                        val fGlucose = track.fGlucose
//                        val tGlucose = track.tGlucose
//                        val hemoglobin = track.hemoglobin
//                        val urine = track.urine
//
//                        if (fGlucose != null) {
//                            if(fGlucose<7.0){
//                                arrayList2.add("Blood Glucose Level (Fasting state) is Normal")
//                            }
//                        }
//
//                        if (tGlucose != null) {
//                            if(tGlucose<11.1){
//                                arrayList2.add("Blood Glucose Level (2h after meal) is Normal")
//                            }
//                        }
//
//                        if (hemoglobin != null) {
//                            if(hemoglobin<6.5){
//                                arrayList2.add("Glycosylated Hemoglobin is Normal")
//                            }
//                        }
//
//                        if (urine != null) {
//                            if(urine<10){
//                                arrayList2.add("Urine Ket Purity is Normal")
//                            }
//                        }
//
//                        Log.d("arrayList2", arrayList2.toString())
//
//                        val adapter = AlertAdapter(this@HealthAlertActivity, arrayList2)
//                        listView2.adapter = adapter
//                    }
//                }else{
//                    notice2.visibility = View.VISIBLE
//                }
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//        })





        //hide action bar
        if (supportActionBar != null) {
            supportActionBar!!.hide()
        }

        //hide status bar
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }

//        val btnHealthAlertActivityProfileFragment: View = findViewById<ImageView>(R.id.alert_back)
//        btnHealthAlertActivityProfileFragment.setOnClickListener {
//            //explicit intent
//            val intent: Intent = Intent(
//                this@HealthAlertActivity,
//                ProfileFragment::class.java)
//
//            startActivity(intent)
//
//        }


//        val back : View = findViewById<ImageButton>(R.id.alert_back)
//        back.setOnClickListener{
//            finish()
//        }
    }
}