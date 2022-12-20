package com.example.goodday

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.widget.*
import com.example.goodday.databinding.ActivityLoginBinding
import com.example.goodday.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    //var binding: ActivityLoginBinding? = null
    private lateinit var email: EditText
    private lateinit var password: EditText

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        email = findViewById(R.id.email)
        password = findViewById(R.id.password)

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance()

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

        //back to main screen
//        val btnLoginActivityOpenInterfaceActivity: View = findViewById<ImageView>(R.id.back)
//        btnLoginActivityOpenInterfaceActivity.setOnClickListener {
//            //explicit intent
//            val intent: Intent = Intent(
//                this@LoginActivity,
//                OpenInterfaceActivity::class.java)
//
//            startActivity(intent)
//
//        }

        //go to forget password screen
        val btnLoginActivityForgetPasswordActivity: View = findViewById<TextView>(R.id.forget_password)
        btnLoginActivityForgetPasswordActivity.setOnClickListener {
            //explicit intent
            val intent: Intent = Intent(
                this@LoginActivity,
                ForgetPasswordActivity::class.java)

            startActivity(intent)

        }

        val btnLoginActivityMainActivity: View = findViewById<TextView>(R.id.btn_continue)
        btnLoginActivityMainActivity.setOnClickListener {
            userLogin()

        }

        val back : View = findViewById<ImageButton>(R.id.back_to)
        back.setOnClickListener{
            finish()
        }
    }

    private fun userLogin(){
        val email: String = email.text.toString().trim()
        val password: String = password.text.toString().trim()

        mAuth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener{ task ->
                if(task.isSuccessful){
                    //explicit intent
                    val intent: Intent = Intent(
                        this@LoginActivity,
                        MainActivity::class.java)

                    startActivity(intent)
                }else{
                    Toast.makeText(this, "login failed", Toast.LENGTH_SHORT).show()
                }
            }
    }


}
