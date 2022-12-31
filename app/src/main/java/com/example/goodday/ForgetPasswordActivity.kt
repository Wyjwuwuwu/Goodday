package com.example.goodday

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ForgetPasswordActivity : AppCompatActivity() {

    private lateinit var email: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        email = findViewById(R.id.et_forget_email)
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

        val btnForgetPasswordActivityCheckValidationActivity: Button = findViewById<Button>(R.id.btn_send)
        btnForgetPasswordActivityCheckValidationActivity.setOnClickListener {
            val email: String = email.text.toString().trim()
            if (email.isEmpty()){
                Toast.makeText(this, "Please Enter Your Email Address", Toast.LENGTH_SHORT).show()
            }else{
                FirebaseAuth.getInstance().sendPasswordResetEmail(email)
                    .addOnCompleteListener{task ->
                        if (task.isSuccessful){
                            Toast.makeText(this, "Email sent successfully to reset your password !", Toast.LENGTH_LONG).show()
                            val intent: Intent = Intent(this@ForgetPasswordActivity,LoginActivity::class.java)
                            startActivity(intent)

                        }else{
                            Toast.makeText(this,task.exception!!.message.toString(), Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }

        val back : View = findViewById<ImageButton>(R.id.back_to)
        back.setOnClickListener{
            finish()
        }
    }
}