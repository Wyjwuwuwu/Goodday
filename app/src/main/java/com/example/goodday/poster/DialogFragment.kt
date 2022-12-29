package com.example.goodday.poster

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.goodday.R
import com.example.goodday.user.User
import androidx.fragment.app.DialogFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import java.util.*

class DialogFragment : DialogFragment() {

    lateinit var uid: String

    private val TAG = "DialogFragment"

    interface OnInputListener {
        fun sendInput(input: String, date: String)
    }
    lateinit var mOnInputListener: OnInputListener

    private lateinit var mInput: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.dialog_fragment, container, false)

        mInput = rootView.findViewById(R.id.et_content)
        val btn_post = rootView.findViewById<Button>(R.id.btn_post)

        val date = Calendar.getInstance().time.toString()

        btn_post.setOnClickListener{
            val input = mInput.text.toString()
            mOnInputListener.sendInput(input,date)
        }

        return rootView
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            mOnInputListener = activity as OnInputListener
        } catch (e: ClassCastException) {
            Log.e(TAG, "onAttach: ClassCastException: ${e.message}")
        }
    }
}