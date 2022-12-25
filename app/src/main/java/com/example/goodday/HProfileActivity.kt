package com.example.goodday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast

class HProfileActivity : AppCompatActivity() {


    var radioGroup_temp: RadioGroup? = null
    lateinit var radioButton_temp: RadioButton
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hprofile)

        getSupportActionBar()?.hide()


        radioGroup_temp = findViewById(R.id.temGroup)


        val ques: ImageButton = findViewById<ImageButton>(R.id.question_hprofile)
        ques.setOnClickListener {
            val intent = Intent(this@HProfileActivity, QuestionActivity::class.java)
            startActivity(intent)
        }
        val back: ImageButton = findViewById<ImageButton>(R.id.ivBackHomeHProfile)
        back.setOnClickListener {
            val intent = Intent(this@HProfileActivity, MainActivity::class.java)
            startActivity(intent)
        }
        val submit: ImageButton = findViewById<ImageButton>(R.id.submit_hprofile)
        submit.setOnClickListener{
            val intSelectButton: Int = radioGroup_temp!!.checkedRadioButtonId
            radioButton_temp = findViewById(intSelectButton)
            Toast.makeText(baseContext, radioButton_temp.text, Toast.LENGTH_SHORT).show()
//            val intent = Intent(this@HProfileActivity, HealthMeasureActivity::class.java)
//            startActivity(intent)
        }


    }


}