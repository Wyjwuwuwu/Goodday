package com.example.goodday

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.goodday.user.User
import com.example.goodday.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity : AppCompatActivity() {
    var binding: ActivitySignUpBinding? = null
    private lateinit var auth: FirebaseAuth
    private lateinit var username: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var btn_continue: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

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


//        val btnSignUpActivityMainActivity: View = findViewById<TextView>(R.id.btn_continue_to_main)
//        btnSignUpActivityMainActivity.setOnClickListener {
//            //explicit intent
//            val intent: Intent = Intent(
//                this@SignUpActivity,
//                MainActivity::class.java
//            )
//
//            startActivity(intent)
//
//        }

        val back: View = findViewById<ImageButton>(R.id.back_to)
        back.setOnClickListener {
            finish()
        }

        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()

        username = findViewById(R.id.username)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        btn_continue = findViewById(R.id.btn_continue_to_main)
        var uid:String = ""

        btn_continue.setOnClickListener {
            val username: String = username.text.toString().trim()
            val email: String = email.text.toString().trim()
            val password: String = password.text.toString().trim()
            Toast.makeText(this, "test1", Toast.LENGTH_SHORT).show()

            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        uid = auth.uid.toString()
                        Log.d("uid",uid)
                        val user = User(uid, username, email)
                        Toast.makeText(this, "test2", Toast.LENGTH_SHORT).show()
                        FirebaseDatabase.getInstance().reference.child("Users")
                            .child(FirebaseAuth.getInstance().currentUser!!.uid)
                            .setValue(user).addOnCompleteListener { task ->
                                if (task.isSuccessful){
                                    Toast.makeText(this, "Sign up successfully", Toast.LENGTH_SHORT).show()
                                }else{
                                    Toast.makeText(this, "Sign up failed", Toast.LENGTH_SHORT).show()
                                }
                            }

                        //explicit intent
                        val intent: Intent = Intent(
                            this@SignUpActivity,
                            LoginActivity::class.java)

                        startActivity(intent)

                    }else{
                        Toast.makeText(this, "test2_failed", Toast.LENGTH_SHORT).show()
                    }

                }
        }

    }
}