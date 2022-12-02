package com.example.goodday

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView


class ChatFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_chat, container, false)
        val rootView = inflater.inflate(R.layout.fragment_chat, container, false)
        val btn_to_chat  = rootView.findViewById<AppCompatTextView>(R.id.annatext)

        btn_to_chat.setOnClickListener{
            val intent = Intent (activity, ChatInterfaceActivity::class.java)
            activity?.startActivity(intent)
    }
        return rootView


    }


}