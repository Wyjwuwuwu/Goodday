package com.example.goodday

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.goodday.feelingModel.Feeling
import com.example.goodday.feelingUI.FeelingViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class HProfileActivity : AppCompatActivity() {
    private val feelingViewModel: FeelingViewModel by viewModels ()
    lateinit var user: FirebaseUser
    lateinit var uid: String

    var radioGroup_temp: RadioGroup? = null
    var radioGroup_puls: RadioGroup? = null
    var radioGroup_blood: RadioGroup? = null
    var radioGroup_breath: RadioGroup? = null
    var radioGroup_shiver: RadioGroup? = null
    lateinit var radioButton_temp: RadioButton
    lateinit var radioButton_puls: RadioButton
    lateinit var radioButton_blood: RadioButton
    lateinit var radioButton_breath: RadioButton
    lateinit var radioButton_shiver: RadioButton

    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)
    val date = "$year-${month + 1}-$day"
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hprofile)

        getSupportActionBar()?.hide()

        user = FirebaseAuth.getInstance().currentUser!!
        uid = user.uid

        radioGroup_temp = findViewById(R.id.temGroup)
        radioGroup_shiver = findViewById(R.id.shiGroup)
        radioGroup_puls= findViewById(R.id.pulsGroup)
        radioGroup_breath = findViewById(R.id.breGroup)
        radioGroup_blood = findViewById(R.id.bloGroup)



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
            val intSelectButtonTemp: Int = radioGroup_temp!!.checkedRadioButtonId
            val intSelectButtonPuls: Int = radioGroup_puls!!.checkedRadioButtonId
            val intSelectButtonBreath: Int = radioGroup_breath!!.checkedRadioButtonId
            val intSelectButtonBlood: Int = radioGroup_blood!!.checkedRadioButtonId
            val intSelectButtonShiver: Int = radioGroup_shiver!!.checkedRadioButtonId

            radioButton_temp = findViewById(intSelectButtonTemp)
            radioButton_puls = findViewById(intSelectButtonPuls)
            radioButton_shiver= findViewById(intSelectButtonShiver)
            radioButton_blood = findViewById(intSelectButtonBlood)
            radioButton_breath = findViewById(intSelectButtonBreath)

            val feeling = Feeling(
                uid,
                Integer.parseInt(radioButton_temp.text as String),
                Integer.parseInt(radioButton_blood.text as String),
                Integer.parseInt(radioButton_shiver.text as String),
                Integer.parseInt(radioButton_breath.text as String),
                Integer.parseInt(radioButton_puls.text as String),
                date
            )

//            Toast.makeText(baseContext, radioButton_temp.text, Toast.LENGTH_SHORT).show()
            lifecycleScope.launch(Dispatchers.IO) {
                feelingViewModel.addFeeling(uid,feeling)
            }
            val intent = Intent(this@HProfileActivity, HealthMeasureActivity::class.java)
            startActivity(intent)
            finish()
        }


    }


}