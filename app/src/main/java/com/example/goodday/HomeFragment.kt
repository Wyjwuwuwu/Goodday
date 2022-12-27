package com.example.goodday
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.example.goodday.user.HealthTrack
import com.example.goodday.user.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import java.util.*


class HomeFragment : Fragment() {

    lateinit var name:String
    lateinit var tv_name:TextView
    lateinit var uid: String
    lateinit var user: FirebaseUser
    lateinit var reference: DatabaseReference
    lateinit var reference2: DatabaseReference
    lateinit var fullname: String
    lateinit var email: String
    lateinit var circularProgressBar:CircularProgressBar

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        circularProgressBar = rootView.findViewById(R.id.circularProgressBar)
        val btn_visualization = rootView.findViewById<AppCompatButton>(R.id.btn_visualization)
        val btn_history = rootView.findViewById<AppCompatButton>(R.id.btn_check)
        val btn_feeling = rootView.findViewById<Button>(R.id.btn_feeling)
        val btn_record = rootView.findViewById<Button>(R.id.btn_record)
        val btn_input = rootView.findViewById<Button>(R.id.btn_input)
        val btn_treatment = rootView.findViewById<AppCompatButton>(R.id.btn_treatment)
        val btn_info = rootView.findViewById<AppCompatButton>(R.id.btn_healthInfo)
        val btn_careinfo = rootView.findViewById<AppCompatButton>(R.id.btn_healthcareInfo)
        val tv_healthScore = rootView.findViewById<TextView>(R.id.tv_healthScore)


        tv_name = rootView.findViewById(R.id.textView)

        user = FirebaseAuth.getInstance().currentUser!!
        reference = FirebaseDatabase.getInstance().getReference("Users")
        reference2 = FirebaseDatabase.getInstance().getReference("Health_Track")
        uid = user.uid


        reference.child(uid).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userProfile = snapshot.getValue(User::class.java) as User

                if (userProfile != null) {
                    fullname = userProfile.fullName
                    email = userProfile.email
                    tv_name.text = "Hello $fullname"

                }
            }

            override fun onCancelled(error: DatabaseError) {
            }

        })

        val date = getdate()
        reference2.child(uid).child(date).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    val health = snapshot.getValue(HealthTrack::class.java) as HealthTrack

                    if(health != null){
                        val healthScore = health.healthScore
                        val score:Float = healthScore!!
                        tv_healthScore.text = score.toString()
                        circle(score)
                    }
                }

            }
            override fun onCancelled(error: DatabaseError) {}
        })


        btn_visualization.setOnClickListener {
            val intent = Intent (activity, VisualizationDashboard::class.java)
            activity?.startActivity(intent)
        }

        btn_history.setOnClickListener {
            val intent = Intent (activity, HealthView::class.java)
            activity?.startActivity(intent)
        }

        btn_feeling.setOnClickListener {
            val intent = Intent (activity, HProfileActivity::class.java)
            activity?.startActivity(intent)
        }

        btn_record.setOnClickListener {
            val intent = Intent (activity, HealthMeasureActivity::class.java)
            activity?.startActivity(intent)
        }

        btn_input.setOnClickListener {
            val intent = Intent (activity, TrackActivity::class.java)
            activity?.startActivity(intent)
        }

        btn_treatment.setOnClickListener {
            val intent = Intent (activity, TreatmentActivity::class.java)
            activity?.startActivity(intent)
        }

        btn_info.setOnClickListener {
            val intent2 = Intent (activity, HealthInfo::class.java)
            activity?.startActivity(intent2)
        }

        btn_careinfo.setOnClickListener {
            val intent = Intent (activity, HealthCareActivity::class.java)
            activity?.startActivity(intent)
        }




        // Inflate the layout for this fragment
        return rootView
    }

    fun circle(score:Float){
        circularProgressBar.apply {

            setProgressWithAnimation(score, 1000) // =1s

            // Set Progress Max
            progressMax = 100f

            // Set ProgressBar Color
            progressBarColor = Color.rgb(88,139,139)

            // Set background ProgressBar Color
            backgroundProgressBarColor = Color.rgb(245,245,245)

            // Set Width
            progressBarWidth = 18f // in DP
            backgroundProgressBarWidth = 18f // in DP

            // Other
            roundBorder = true
            startAngle = 0f
            progressDirection = CircularProgressBar.ProgressDirection.TO_RIGHT
        }
    }

    fun getdate(): String {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val date = "$year-${month + 1}-$day"

        return date
    }

}