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

        btn_history.setOnClickListener {
            val intent = Intent (activity, HealthView::class.java)
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