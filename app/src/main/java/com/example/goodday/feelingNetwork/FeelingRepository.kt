package com.example.goodday.feelingNetwork

import android.opengl.Visibility
import android.util.Log
import android.view.View
import android.view.View.VISIBLE
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import com.example.goodday.databinding.ActivityHealthMeasureBinding
import com.example.goodday.feelingModel.Feeling
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import java.util.*
import java.util.logging.Logger.getLogger
import kotlin.collections.ArrayList


class FeelingRepository {
    var firebaseDatabase: FirebaseDatabase = FirebaseDatabase.getInstance()
    lateinit var databaseReference: DatabaseReference
    var test : String = ""
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val date = "$year-${month + 1}-$day"


    fun addFeeling(id: String, feeling: Feeling) {

        var status: MutableLiveData<Boolean> = MutableLiveData(false)


//        firebaseDatabase = FirebaseDatabase.getInstance()
        // on below line creating our database reference.
        databaseReference = firebaseDatabase.getReference("Feeling")  // Course collection (table)
        databaseReference.child(id).child(date)
            .setValue(feeling)
    }

    fun getAllFeeling(): MutableLiveData<ArrayList<Feeling>> {

        val allFeeling: MutableLiveData<ArrayList<Feeling>> = MutableLiveData<ArrayList<Feeling>>()
        lateinit var user: FirebaseUser
        user = FirebaseAuth.getInstance().currentUser!!
        val uid = user.uid
        databaseReference = firebaseDatabase.getReference("Feeling")
        val feeling = ArrayList<Feeling>()
        val TAG = "Feeling"
        databaseReference.child(uid).addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                for (snapshot in snapshot.children) {
                    val track = snapshot.getValue(Feeling::class.java) as Feeling
                    if(track!=null){
                        feeling.add(track!!)
                    }
                }
                allFeeling.setValue(feeling)
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
        return allFeeling
    }

    fun getLatestFeeling(): MutableLiveData<ArrayList<Feeling>> {
        val latestFeeling: MutableLiveData<ArrayList<Feeling>> = MutableLiveData<ArrayList<Feeling>>()
        lateinit var user: FirebaseUser
        user = FirebaseAuth.getInstance().currentUser!!
        val uid = user.uid
        databaseReference = firebaseDatabase.getReference("Feeling")
        val feeling = ArrayList<Feeling>()
        val TAG = "Feeling"

        databaseReference.child(uid).child(date).addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if(snapshot.exists()){
                    val track = snapshot.getValue(Feeling::class.java) as Feeling
                    if(track!=null){
                        feeling.add(track!!)
                    }
                    latestFeeling.setValue(feeling)
                }else{
                    latestFeeling.setValue(feeling)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        return latestFeeling
    }
    companion object {
        private var feelingRepository: FeelingRepository? = null
        fun getInstance(): FeelingRepository?
        {
            if (feelingRepository == null) {
                feelingRepository = FeelingRepository()
            }
            return feelingRepository
        }
    }

}