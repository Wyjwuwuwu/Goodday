package com.example.goodday

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import com.example.goodday.user.User
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import java.util.*

class EditProfileActivity : AppCompatActivity() {

    //lateinit var fullname_field: TextView
    //lateinit var email_profile: TextInputEditText
    lateinit var name_profile: TextInputEditText
    lateinit var uid: String
    lateinit var user: FirebaseUser
    lateinit var reference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        user = FirebaseAuth.getInstance().currentUser!!
        reference = FirebaseDatabase.getInstance().getReference("Users")
        uid = user.uid

        //fullname_field = findViewById(R.id.fullname_field)
        //email_profile = findViewById(R.id.email_profile)
        name_profile = findViewById(R.id.name_profile)

        displayProfile()




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

        //go to reset password screen
        val btnEditProfileActivityForgetPasswordActivity: View = findViewById<TextView>(R.id.change_password)
        btnEditProfileActivityForgetPasswordActivity.setOnClickListener {
            //explicit intent
            val intent: Intent = Intent(
                this@EditProfileActivity,
                ForgetPasswordActivity::class.java)

            startActivity(intent)

        }

        val back : View = findViewById<ImageButton>(R.id.back_to)
        back.setOnClickListener{
            //finish()
            val intent: Intent = Intent(
                this@EditProfileActivity,
                MainActivity::class.java)

            startActivity(intent)
        }

        val update : View = findViewById<Button>(R.id.btn_update)
        update.setOnClickListener{

            reference.child(uid).addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val userProfile = snapshot.getValue(User::class.java) as User

                    if(userProfile != null){
                        val name:String=userProfile.fullName
                        //val email:String=userProfile.email

                        if (!name_profile.equals(name)){

                            reference.child(uid).child("fullName").setValue(name_profile.text.toString())

                            displayProfile()
                            //name_profile.setText(name)
                            //Toast.makeText(this, "User Name has been Changed !", Toast.LENGTH_LONG).show()
                        }

//                        if (!email_profile.equals(email)){
//
//                            reference.child(uid).child("email").setValue(email_profile.text.toString())
//
//                            displayProfile()
//
//                        }
                    }

                }
                override fun onCancelled(error: DatabaseError) {
                    // code here
                }
            })


        }

    }

    private fun displayProfile() {
        reference.child(uid).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val userProfile = snapshot.getValue(User::class.java) as User

                if(userProfile != null){
                    //val fullname:String = userProfile.fullName
                    val email:String = userProfile.email
                    val name:String=userProfile.fullName

                    //Log.d("email", email)
                    //fullname_field.text = fullname
                    //email_profile.setText(email)
                    name_profile.setText(name)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // code here
            }
        })
    }
}