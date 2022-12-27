package com.example.goodday.adapter

import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.goodday.HealthAlertActivity
import com.example.goodday.R

class AlertAdapter(private val context: HealthAlertActivity,
                   private val content: ArrayList<String>)
    : ArrayAdapter<String>(context, R.layout.recycle_view_layout_alert,content){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.recycle_view_layout_alert, null, true)

        val titleText = rowView.findViewById(R.id.tv_result_alert) as TextView

        titleText.text = content[position]


        return rowView}

    }