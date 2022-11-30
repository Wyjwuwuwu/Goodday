package com.example.goodday
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.mikhaellopez.circularprogressbar.CircularProgressBar


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        val circularProgressBar = rootView.findViewById<CircularProgressBar>(R.id.circularProgressBar)
        val btn_visualization = rootView.findViewById<AppCompatButton>(R.id.btn_visualization)
        val btn_history = rootView.findViewById<AppCompatButton>(R.id.btn_check)
        val btn_feeling = rootView.findViewById<Button>(R.id.btn_feeling)
        val btn_record = rootView.findViewById<Button>(R.id.btn_record)
        val btn_input = rootView.findViewById<Button>(R.id.btn_input)
        val btn_treatment = rootView.findViewById<AppCompatButton>(R.id.btn_treatment)
        val btn_info = rootView.findViewById<AppCompatButton>(R.id.btn_healthInfo)
        val btn_careinfo = rootView.findViewById<AppCompatButton>(R.id.btn_healthcareInfo)

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


        circularProgressBar.apply {
            // Set Progress
            //progress = 85f
            // or with animation
            setProgressWithAnimation(86f, 1000) // =1s

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
        // Inflate the layout for this fragment
        return rootView
    }



}